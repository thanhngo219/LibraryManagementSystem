package com.library.dao;

import java.io.*;
import java.util.List;

public class DataAccessSystem implements DataAccess {

    @Override
    public void readFile(Dao dao) {
        try {
            String fileUrl = dao.getUrl();
            //Read from the stored file
            FileInputStream fileInputStream = new FileInputStream(new File(
                    fileUrl));
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            dao.unpackData(input.readObject());
            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeFile(Dao dao) {
        try {
            String fileUrl = dao.getUrl();
            // Store Serialized User Object in File
            FileOutputStream fileOutputStream = new FileOutputStream(
                    fileUrl);

            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            output.writeObject(dao.getObjects());

            output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
