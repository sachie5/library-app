package org.example.data;

public class Commands {

    private static final String[] entryCommands = new String[] {"1: Visitor", "2: Admin"};
    private static final String[] visitorCommands = new String[]{"1: Get a list of the library directory", "2: Borrow a book", "3: Leave the library"};
    private static  final String[] adminCommands = new String[] {"1: See books on loan", "2: See loan data for book"};

    public String[] getVisitorCommands() {
        return visitorCommands;
    }

    public String[] getAdminCommands() {
        return adminCommands;
    }

    public String[] getEntryCommands() {
        return entryCommands;
    }


}
