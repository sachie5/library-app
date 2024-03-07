package org.example.data;

public class Commands {

    private final String[] entryCommands = new String[] {"1: Visitor", "2: Admin"};
    private String[] visitorCommands = new String[]{"1: Get a list of the library directory", "2: Borrow a book", "3: Leave the library"};
    private String[] adminCommands = new String[] {"1: See books on loan", "2: See loan data for book"};
    public String[] getVisitorCommands() {
        return visitorCommands;
    }

    public void setVisitorCommands(String[] visitorCommands) {
        this.visitorCommands = visitorCommands;
    }

    public String[] getAdminCommands() {
        return adminCommands;
    }

    public void setAdminCommands(String[] adminCommands) {
        this.adminCommands = adminCommands;
    }

    public String[] getEntryCommands() {
        return entryCommands;
    }
}
