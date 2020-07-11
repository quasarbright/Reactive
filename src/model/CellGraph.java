package model;

import expressions.Expr;
import expressions.values.ErrorValue;
import expressions.values.Value;

import java.util.*;
import java.util.stream.Collectors;

public class CellGraph<Name> implements Model<Name> {
    /*
    Invariants:
    - env is up to date
    - keys of cells, parents, and children are the same
     */
    private Map<Name, Optional<Cell<Name>>> cells;
    private Map<Name, Optional<Cell<Name>>> cells_;
    // maps nodes to their parents
    private Map<Name, Set<Name>> parents;
    private Map<Name, Set<Name>> parents_;
    // maps nodes to their children
    // use Names instead of cells because cells may not exist yet
    private Map<Name, Set<Name>> children;
    private Map<Name, Set<Name>> children_;

    public CellGraph() {
        this.cells = new HashMap<>();
        this.parents = new HashMap<>();
        this.children = new HashMap<>();
        this.cells_ = new HashMap<>();
        this.parents_ = new HashMap<>();
        this.children_ = new HashMap<>();
    }

    private void backup() {
        this.cells_ = new HashMap<>(this.cells);
        this.parents_ = new HashMap<>(this.parents);
        this.children_ = new HashMap<>(this.children);
    }

    private void restore() {
        this.cells = new HashMap<>(this.cells_);
        this.parents = new HashMap<>(this.parents_);
        this.children = new HashMap<>(this.children_);
    }

    private void ensureKey(Name name) {
        if(!this.cells.containsKey(name)) {
            this.cells.put(name, Optional.empty());
        }
        if(!this.parents.containsKey(name)) {
            this.parents.put(name, new HashSet<>());
        }
        if(!this.children.containsKey(name)) {
            this.children.put(name, new HashSet<>());
        }
    }

    private Set<Name> getParents(Name name) {
        this.ensureKey(name);
        return this.parents.get(name);
    }

    private Set<Name> getChildren(Name name) {
        this.ensureKey(name);
        return this.children.get(name);
    }

    private void disown(Name parent, Name child) {
        this.ensureKey(parent);
        this.ensureKey(child);
        this.parents.get(child).remove(parent);
        this.children.get(parent).remove(child);
    }

    private void adopt(Name parent, Name child) {
        this.ensureKey(parent);
        this.ensureKey(child);
        this.parents.get(child).add(parent);
        this.children.get(parent).add(child);
    }

    @Override
    public Cell<Name> getCell(Name name) {
        if (this.cells.containsKey(name)) {
            Optional<Cell<Name>> maybeCell = this.cells.get(name);
            if (maybeCell.isPresent()) {
                return maybeCell.get();
            }
        }
        throw new IllegalArgumentException("name "+name+" not found");
    }

    @Override
    public Expr<Name> getExpr(Name name) {
        Cell<Name> cell = this.getCell(name);
        return cell.expr;
    }

    @Override
    public Value<Name> getValue(Name name) {
        if(this.cells.containsKey(name)) {
            Optional<Cell<Name>> maybeCell = this.cells.get(name);
            if(maybeCell.isPresent()) {
                Cell<Name> cell = maybeCell.get();
                return cell.getValue();
            }
        }
        return new ErrorValue<>("name "+name+" not found");
    }

    @Override
    public Set<Name> getNames() {
        return this.cells.keySet().stream()
                .filter(name -> this.cells.get(name).isPresent())
                .collect(Collectors.toSet());
    }

    @Override
    public Map<Name, Value<Name>> getValues() {
        Map<Name, Value<Name>> ans = new HashMap<>();
        this.cells.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach((Cell<Name> cell) -> ans.put(cell.name, cell.getValue()));
        return ans;
    }

    private boolean cycleCheck(Name name) {
        Stack<Name> workList = new Stack<>();
        Set<Name> seen = new HashSet<>();
        this.getChildren(name).forEach(workList::push);
        while(!workList.empty()) {
            Name curr = workList.pop();
            if(curr.equals(name)) {
                return true;
            }
            if(seen.contains(curr)) {
                continue;
            } else {
                seen.add(curr);
            }
            Set<Name> children = this.getChildren(curr);
            children.forEach(workList::push);
        }
        return false;
    }

    /**
     * Topological sort of the parents of the root (for back prop)
     * @param root the root of the back prop
     * @return the ordered list of names to update
     */
    private List<Name> topSort(Name root) {
        List<Name> ans = new ArrayList<>();
        Set<Name> seen = new HashSet<>();
        this.topSortHelp(root, seen, ans);
        Collections.reverse(ans);
        return ans;
    }

    private void topSortHelp(Name curr, Set<Name> seen, List<Name> ans) {
        Set<Name> parents = this.getParents(curr);
        seen.add(curr);
        parents.forEach(parent -> {
            if(!seen.contains(parent)) {
                topSortHelp(parent, seen, ans);
            }
        });
        ans.add(curr);
    }

    @Override
    public void setCell(Name parent, Expr<Name> expr) {
        this.backup();
        this.ensureKey(parent);

        Set<Name> newChildren = expr.getFreeVars();
        newChildren.forEach(this::ensureKey);

        Set<Name> oldChildren = this.getChildren(parent);

        // update connections
        oldChildren.forEach(child -> this.disown(parent, child));
        newChildren.forEach(child -> this.adopt(parent, child));


        if(this.cycleCheck(parent)) {
            this.restore();
            throw new IllegalStateException("cyclic definition");
        }

        // back prop
        this.cells.put(parent, Optional.of(new Cell<>(parent, expr, this::getValue)));

        List<Name> nodes = this.topSort(parent);
        nodes.forEach(name ->
                this.cells.get(name).ifPresent(
                        cell -> cell.reevaluate(this::getValue)));
    }
}
