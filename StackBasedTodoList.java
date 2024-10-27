import java.util.*;
public class StackBasedTodoList {
    // Task class to represent individual tasks
    static class Task {
        private String description;
        private String priority;

        public Task(String description, String priority) {
            this.description = description;
            this.priority = priority;
        }

        public String getDescription() {
            return description;
        }

        public String getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return "Task(description='" + description + "', priority='" + priority + "')";
        }
    }

    // StackBasedTodoList class to manage tasks
    private Stack<Task> tasks;

    public StackBasedTodoList() {
        tasks = new Stack<>();
    }

    // Method to add a task
    public void addTask(String description, String priority) {
        Task task = new Task(description, priority);
        tasks.push(task);
        System.out.println("Added: " + task);
    }

    // Method to remove a task
    public void removeTask() {
        if (!tasks.isEmpty()) {
            Task removedTask = tasks.pop();
            System.out.println("Removed: " + removedTask);
        } else {
            System.out.println("No tasks to remove.");
        }
    }

    // Method to display tasks sorted by priority
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
// Create a list from the stack and sort by priority
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparingInt(task -> {
            switch (task.getPriority()) {
                case "High":
                    return 1;
                case "Medium":
                    return 2;
                case "Low":
                    return 3;
                default:
                    return 4;
            }
        }));
        for (Task task : sortedTasks) {
            System.out.println(task);
        }
    }

    public class Assignment4 {
        public static void main(String[] args) {
            StackBasedTodoList todoList = new StackBasedTodoList();
            Scanner scanner = new Scanner(System.in);
            String input;
            while (true) {
                System.out.println("\nTo-Do List Menu:");
                System.out.println("1. Add Task");
                System.out.println("2. Remove Task");
                System.out.println("3. Display Tasks");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                input = scanner.nextLine();
                switch (input) {
                    case "1":
                        System.out.print("Enter task description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter task priority (High, Medium, Low): ");
                        String priority = scanner.nextLine();
                        todoList.addTask(description, priority);
                        break;
                    case "2":
                        todoList.removeTask();
                        break;
                    case "3":
                        System.out.println("\nAll Tasks:");
                        todoList.displayTasks();
                        break;
                    case "4":
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }
}
