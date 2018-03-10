package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EventPlanner;
import seedu.address.model.ReadOnlyEventPlanner;

/**
 * An Immutable EventPlanner that is serializable to XML format
 */
@XmlRootElement(name = "addressbook")
public class XmlSerializableEventPlanner {

    @XmlElement
    private List<XmlAdaptedPerson> persons;
    @XmlElement
    private List<XmlAdaptedTag> tags;

    /**
     * Creates an empty XmlSerializableEventPlanner.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableEventPlanner() {
        persons = new ArrayList<>();
        tags = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableEventPlanner(ReadOnlyEventPlanner src) {
        this();
        persons.addAll(src.getPersonList().stream().map(XmlAdaptedPerson::new).collect(Collectors.toList()));
        tags.addAll(src.getTagList().stream().map(XmlAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this addressbook into the model's {@code EventPlanner} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedPerson} or {@code XmlAdaptedTag}.
     */
    public EventPlanner toModelType() throws IllegalValueException {
        EventPlanner eventPlanner = new EventPlanner();
        for (XmlAdaptedTag t : tags) {
            eventPlanner.addTag(t.toModelType());
        }
        for (XmlAdaptedPerson p : persons) {
            eventPlanner.addPerson(p.toModelType());
        }
        return eventPlanner;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableEventPlanner)) {
            return false;
        }

        XmlSerializableEventPlanner otherAb = (XmlSerializableEventPlanner) other;
        return persons.equals(otherAb.persons) && tags.equals(otherAb.tags);
    }
}