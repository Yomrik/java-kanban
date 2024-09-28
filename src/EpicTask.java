import java.util.ArrayList;

public class EpicTask extends Task {
    ArrayList<Integer> subTaskIds = new ArrayList<>();


    public EpicTask(StatusTask status, String description, String name) {
        super(status, description, name);
    }
}

