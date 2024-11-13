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

    public abstract void saveData();

    //public abstract boolean checkLogin(String username, String password);

    public String getFilePath()
    {
        return FILE_PATH;
    }

    public ArrayList<T> getDataArray()
    {
        return dataArray;
    }

    public void setDataArray(ArrayList<T> dataArray)
    {
        this.dataArray = dataArray;
    }
}
