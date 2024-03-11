package org.example.data;

public class Commands {
    private static final String[] entryCommands = new String[] {"1: Visitor", "2: Admin", "3: Member"};
    protected static final String[] userCommands = new String[]{"1: Get a list of the library directory", "2: Borrow a book", "3: Leave the library"};
    protected static final String[] visitorCommands = new String[] {"1: Get a list of the library directory", "2: Read a book", "3: Become a member" ,"4: Leave the library"};
    protected static  final String[] adminCommands = new String[] {"1: See books on loan", "2: See loan data for book"};

    public String[] getUserCommands() {
        return userCommands;
    }

    public String[] getAdminCommands() {
        return adminCommands;
    }

    public String[] getEntryCommands() {
        return entryCommands;
    }

    public String[] getVisitorCommands(){ return visitorCommands; }

}
