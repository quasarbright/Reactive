package cells;

public class CellGraphTest extends ModelTest {
    @Override
    protected Model<String> factory() {
        return new CellGraph<>();
    }
}
