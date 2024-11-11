package ServiceClasses.Database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FileHandler {
    private List<String[]> dataArray = new ArrayList<>();
    private String FILE_PATH;

    public FileHandler(String FILE_PATH)
    {
        this.FILE_PATH = FILE_PATH;
    }

    public List<String[]> getDataArray()
    {
        return dataArray;
    }

    public String getFilePath()
    {
        return FILE_PATH;
    }

    public List<String[]> getData()
    {
        dataArray.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] rows = line.split(",");
                dataArray.add(rows);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return dataArray;
    }

    public void save(List<String[]> patientData)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] row : patientData) {
                for (String column : row) {
                    writer.append(column)
                            .append(",");
                }
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
