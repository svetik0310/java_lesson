package ru.zotkina.addressbook.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    @Parameter(names="-d",description = "File format")
    public String format;

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
        if (format.equals("csv")) {
            saveFileCSV(contacts, new File(file));
        } else if (format.equals("json")) {
            saveFileJSON(contacts, new File(file));
        }else System.out.println("Unrecognized format"+format);
    }

    private List<ContactData> getContacts(int count) {
        List<ContactData> contacts=new ArrayList<ContactData>();
        for (int i=0;i<count;i++)
        {
            contacts.add(new ContactData()
                    .withFirstname(String.format("Firstname%s",i))
                    .withMiddlename(String.format("Midlename%s",i))
                    .withLastname(String.format("Lastname%s",i))
                    .withNickname(String.format("Nickname%s",i))
                    .withTitle(String.format("Title%s",i))
                    .withCompany(String.format("Company%s",i))
                    .withAddress(String.format("Address%s",i))
                    .withHome(String.format("Home%s",i))
                    .withMobile(String.format("Mobile%s",i))
                    .withWork(String.format("Work%s",i))
                    .withFax(String.format("Fax%s",i))
                    .withEmail(String.format("email%s@mail.ru",i))
                    .withEmail2(String.format("email2%s@mail.ru",i))
                    .withEmail3(String.format("email3%s@mail.ru",i))
                    .withHomepage(String.format("homepage%s.ru",i))
                    .withGroup(String.format("groupname %s",i))
            );
        }
        return contacts;
    }

    private void saveFileCSV(List<ContactData> contacts, File file) throws IOException {
        try(Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s\n", contact.getFirstname(), contact.getLastname()));
            }
        }
    }

    private void saveFileJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }
}
