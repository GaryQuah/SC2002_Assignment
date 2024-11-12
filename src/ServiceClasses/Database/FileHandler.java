package ServiceClasses.Database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FileHandler<T> {
    private ArrayList<T> dataArray = new ArrayList<>();
    private String FILE_PATH;

    public FileHandler(String FILE_PATH)
    {
        this.FILE_PATH = FILE_PATH;
    }

    public abstract ArrayList<T> retrieveData();

    public abstract ArrayList<T> saveData();

    public String getFilePath()
    {
        return FILE_PATH;
    }

    public ArrayList<T> getDataArray()
    {
        return dataArray;
    }
}
