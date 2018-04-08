package ru.zotkina.addressbook.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.zotkina.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names="-c",description = "Group count")
    public int count;

    @Parameter(names="-f",description = "File name")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {jCommander.parse(args);}
        catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = getContacts(count);
        saveToFile(contacts,new File(file));
    }

    private List<ContactData> getContacts(int count) {
        List<ContactData> contacts=new ArrayList<ContactData>();
        for (int i=0;i<count;i++)
        {
            contacts.add(new ContactData().withLastname(String.format("Lastname%s",i))
                    .withFirstname(String.format("Firstname%s",i)));
        }
        return contacts;
    }

    private void saveToFile(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(ContactData contact: contacts)
        {
            writer.write(String.format("%s;%s\n",contact.getFirstname(),contact.getLastname()));
        }
        writer.close();
    }
}
