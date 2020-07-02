package cells;

import expressions.Expr;
import expressions.values.ErrorValue;
import expressions.values.Value;

import java.util.*;

public class CellGraph implements Model {
    /*
    Invariants:
    - env is up to date
    - keys of cells, parents, and children are the same
     */
    private Map<String, Optional<Cell>> cells;
    private Map<String, Optional<Cell>> cells_;
    // maps nodes to their parents
    private Map<String, Set<String>> parents;
    private Map<String, Set<String>> parents_;
    // maps nodes to their children
    // use strings instead of cells because cells may not exist yet
    private Map<String, Set<String>> children;
    private Map<String, Set<String>> children_;

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

    private void ensureKey(String name) {
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

    private Set<String> getParents(String name) {
        this.ensureKey(name);
        return this.parents.get(name);
    }

    private Set<String> getChildren(String name) {
        this.ensureKey(name);
        return this.children.get(name);
    }

    private void disown(String parent, String child) {
        this.ensureKey(parent);
        this.ensureKey(child);
        this.parents.get(child).remove(parent);
        this.children.get(parent).remove(child);
    }

    private void adopt(String parent, String child) {
        this.ensureKey(parent);
        this.ensureKey(child);
        this.parents.get(child).add(parent);
        this.children.get(parent).add(child);
    }

    @Override
    public Cell getCell(String name) {
        if (this.cells.containsKey(name)) {
            Optional<Cell> maybeCell = this.cells.get(name);
            if (maybeCell.isPresent()) {
                return maybeCell.get();
            }
        }
        throw new IllegalArgumentException("name "+name+" not found");
    }

    @Override
    public Expr getExpr(String name) {
        Cell cell = this.getCell(name);
        return cell.expr;
    }

    @Override
    public Value getValue(String name) {
        if(this.cells.containsKey(name)) {
            Optional<Cell> maybeCell = this.cells.get(name);
            if(maybeCell.isPresent()) {
                Cell cell = maybeCell.get();
                return cell.getValue();
            }
        }
        return new ErrorValue("name "+name+" not found");
    }

    @Override
    public Map<String, Value> getValues() {
        Map<String, Value> ans = new HashMap<>();
        this.cells.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach((Cell cell) -> ans.put(cell.name, cell.getValue()));
        return ans;
    }

    private boolean cycleCheck(String name) {
        Stack<String> workList = new Stack<>();
        Set<String> seen = new HashSet<>();
        this.getChildren(name).forEach(workList::push);
        while(!workList.empty()) {
            String curr = workList.pop();
            if(curr.equals(name)) {
                return true;
            }
            if(seen.contains(curr)) {
                continue;
            } else {
                seen.add(curr);
            }
            Set<String> children = this.getChildren(curr);
            children.forEach(workList::push);
        }
        return false;
    }

    /**
     * Topological sort of the parents of the root (for backprop)
     * @param root the root of the backprop
     * @return the ordered list of names to update
     */
    private List<String> topSort(String root) {
        List<String> ans = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        this.topSortHelp(root, seen, ans);
        Collections.reverse(ans);
        return ans;
    }

    private void topSortHelp(String curr, Set<String> seen, List<String> ans) {
        Set<String> parents = this.getParents(curr);
        seen.add(curr);
        parents.forEach(parent -> {
            if(!seen.contains(parent)) {
                topSortHelp(parent, seen, ans);
            }
        });
        ans.add(curr);
    }

    @Override
    public void setCell(String parent, Expr expr) {
        this.backup();
        this.ensureKey(parent);

        Set<String> newChildren = expr.getFreeVars();
        newChildren.forEach(this::ensureKey);

        Set<String> oldChildren = this.getChildren(parent);

        // update connections
        oldChildren.forEach(child -> this.disown(parent, child));
        newChildren.forEach(child -> this.adopt(parent, child));


        if(this.cycleCheck(parent)) {
            this.restore();
            throw new IllegalStateException("cyclic definition");
        }

        // backprop
        this.cells.put(parent, Optional.of(new Cell(parent, expr, this::getValue)));

        List<String> nodes = this.topSort(parent);
        nodes.forEach(name ->
                this.cells.get(name).ifPresent(
                        cell -> cell.reevaluate(this::getValue)));


//        Set<String> seen = new HashSet<>();
//        Stack<String> workList = new Stack<>();
//        workList.push(parent);
//        while(!workList.empty()) {
//            String curr = workList.pop();
//            if(seen.contains(curr)) {
//                continue;
//            }
//            this.cells.get(curr).ifPresent(cell -> cell.reevaluate(this::getValue));
//            seen.add(curr);
//            Set<String> parents = this.getParents(curr);
//            parents.forEach(workList::push);
//        }
    }
}
