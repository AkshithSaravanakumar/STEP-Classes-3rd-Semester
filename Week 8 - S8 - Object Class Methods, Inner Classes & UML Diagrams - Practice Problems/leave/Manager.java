package leave;

/**
 * Concrete reviewer. Manager approves by default and rejects on request; both
 * paths go through {@link LeaveRequest}'s own guarded transitions.
 *
 * UML: Manager ..|> Reviewer (realization)
 *        Manager "1" -- "0..*" LeaveRequest (review)
 */
public class Manager implements Reviewer {

    private final String name;

    public Manager(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean review(LeaveRequest request) {
        if (!request.approve(name)) {
            return false;
        }
        System.out.println(request + " approved.");
        System.out.println("Status: " + request.getStatus() + ".");
        return true;
    }

    @Override
    public boolean reject(LeaveRequest request, String reason) {
        if (!request.reject(name, reason)) {
            return false;
        }
        System.out.println(request + " rejected.");
        System.out.println("Status: " + request.getStatus() + ".");
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}