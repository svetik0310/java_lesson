package ru.zotkina.addressbook.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.zotkina.addressbook.model.GroupData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names="-c",description = "Group count")
    public int count;

    @Parameter(names="-f",description = "File name")
    public String file;

    @Parameter(names="-d",description = "File format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {jCommander.parse(args);}
        catch (ParameterException ex) {
        jCommander.usage();
        return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = getGroups(count);
        if (format.equals("csv")) {
            saveFileCSV(groups, new File(file));
        } else if (format.equals("json")) {
            saveFileJSON(groups, new File(file));
        }else System.out.println("Unrecognized format"+format);
    }

    private void saveFileJSON(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try(Writer writer = new FileWriter(file))
        {writer.write(json);}
    }

    private List<GroupData> getGroups(int count) {
        List<GroupData> groups=new ArrayList<GroupData>();
        for (int i=0;i<count;i++)
        {
            groups.add(new GroupData().withGroupname(String.format("groupname %s",i))
                    .withFooter(String.format("foter %s",i))
            .withHeader(String.format("header %s",i)));
        }
        return groups;
    }

    private void saveFileCSV(List<GroupData> groups, File file) throws IOException {
        try(Writer writer = new FileWriter(file)) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n", group.getGroupname(), group.getHeader(), group.getFooter()));
            }
        }
    }


}
