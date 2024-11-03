import java.util.LinkedList;

public class ActivityList {
    private LinkedList<String> undoList;
    private LinkedList<String> redoList;

    public ActivityList() {
        undoList = new LinkedList<>();
        redoList = new LinkedList<>();
    }

    public void performAction(String action) {
        undoList.addFirst(action);
        redoList.clear();
    }

    public String undo() {
        if (!undoList.isEmpty()) {
            String action = undoList.removeFirst();
            redoList.addFirst(action);
            return "Undid: " + action;
        }
        return "No actions to undo";
    }

    public String redo() {
        if (!redoList.isEmpty()) {
            String action = redoList.removeFirst();
            undoList.addFirst(action);
            return "Redid: " + action;
        }
        return "No actions to redo";
    }
}
