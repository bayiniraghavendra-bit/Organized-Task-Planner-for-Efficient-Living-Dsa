import java.util.*;

// ===== Task Class =====
class Task {
    int id;
    String title;
    String dueDate;
    String priority;
    boolean completed;

    Task(int id, String title, String dueDate, String priority) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
    }
}

// ===== Linked List Task Storage =====
class TaskList {

    LinkedList<Task> tasks = new LinkedList<>();

    // Add Task
    void addTask(Task t) {
        tasks.add(t);
        System.out.println("Task added successfully.");
    }

    // Display Tasks
    void displayTasks() {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (Task t : tasks) {

            System.out.println("----------------------------------");
            System.out.println("Task ID: " + t.id);
            System.out.println("Title: " + t.title);
            System.out.println("Due Date: " + t.dueDate);
            System.out.println("Priority: " + t.priority);
            System.out.println("Completed: " + t.completed);
        }
    }

    // Linear Search
    Task searchTask(int id) {

        for (Task t : tasks) {

            if (t.id == id)
                return t;
        }

        return null;
    }

    // Bubble Sort by Priority
    void sortTasks() {

        for (int i = 0; i < tasks.size(); i++) {

            for (int j = 0; j < tasks.size() - 1; j++) {

                if (tasks.get(j).priority.compareTo(tasks.get(j + 1).priority) > 0) {

                    Task temp = tasks.get(j);
                    tasks.set(j, tasks.get(j + 1));
                    tasks.set(j + 1, temp);
                }
            }
        }

        System.out.println("Tasks sorted by priority.");
    }

}

// ===== Main Class =====
public class TaskPlannerSystem {

    static TaskList taskList = new TaskList();

    static Queue<Task> taskQueue = new LinkedList<>();

    static Stack<Task> completedTasks = new Stack<>();

    static HashMap<Integer, Task> taskMap = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Organized Task Planner =====");

            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Search Task");
            System.out.println("4. Sort Tasks by Priority");
            System.out.println("5. Add Task to Queue");
            System.out.println("6. Complete Task");
            System.out.println("7. View Completed Tasks");
            System.out.println("8. Delete Task");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // Add Task
                case 1:

                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Task Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Due Date: ");
                    String dueDate = sc.nextLine();

                    System.out.print("Enter Priority (Low/Medium/High): ");
                    String priority = sc.nextLine();

                    Task newTask = new Task(id, title, dueDate, priority);

                    taskList.addTask(newTask);

                    taskMap.put(id, newTask);

                    break;

                // View Tasks
                case 2:

                    taskList.displayTasks();

                    break;

                // Search Task
                case 3:

                    System.out.print("Enter Task ID to search: ");
                    int searchId = sc.nextInt();

                    Task foundTask = taskList.searchTask(searchId);

                    if (foundTask != null) {

                        System.out.println("Task Found:");
                        System.out.println("Title: " + foundTask.title);

                    } else {

                        System.out.println("Task not found.");
                    }

                    break;

                // Sort Tasks
                case 4:

                    taskList.sortTasks();

                    break;

                // Add Task to Queue
                case 5:

                    System.out.print("Enter Task ID to add to queue: ");

                    int queueId = sc.nextInt();

                    Task queueTask = taskMap.get(queueId);

                    if (queueTask != null) {

                        taskQueue.add(queueTask);

                        System.out.println("Task added to queue.");

                    } else {

                        System.out.println("Task not found.");
                    }

                    break;

                // Complete Task
                case 6:

                    if (!taskQueue.isEmpty()) {

                        Task completed = taskQueue.remove();

                        completed.completed = true;

                        completedTasks.push(completed);

                        System.out.println("Task Completed: " + completed.title);

                    } else {

                        System.out.println("No tasks in queue.");
                    }

                    break;

                // View Completed Tasks
                case 7:

                    if (completedTasks.isEmpty()) {

                        System.out.println("No completed tasks.");

                    } else {

                        System.out.println("Completed Tasks:");

                        for (Task t : completedTasks) {

                            System.out.println(t.title);
                        }
                    }

                    break;

                // Delete Task
                case 8:

                    System.out.print("Enter Task ID to delete: ");

                    int deleteId = sc.nextInt();

                    Task deleteTask = taskList.searchTask(deleteId);

                    if (deleteTask != null) {

                        taskList.tasks.remove(deleteTask);

                        taskMap.remove(deleteId);

                        System.out.println("Task deleted.");

                    } else {

                        System.out.println("Task not found.");
                    }

                    break;

                case 9:

                    System.out.println("Thank you for using Task Planner.");

                    System.exit(0);
            }
        }
    }
}
