package seedu.address.logic.commands;


import seedu.address.logic.commands.exceptions.CommandException;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

public class ListEventCommand extends Command {

    public static final String COMMAND_WORD = "list-events";

    public static final String MESSAGE_SUCCESS = "Listed all events";

    @Override
    public CommandResult execute() {
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }


}
