package ru.zotkina.addressbook.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
        saveToFile(groups,new File(file));
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

    private void saveToFile(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(GroupData group: groups)
        {
            writer.write(String.format("%s;%s;%s\n",group.getGroupname(),group.getHeader(),group.getFooter()));
        }
        writer.close();
    }


}
