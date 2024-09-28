
public class Task {
    private final String name;
    private final String description;
    private int id;
    public StatusTask status;


    public Task(StatusTask status, String description, String name) {
        this.status = status;
        this.description = description;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
