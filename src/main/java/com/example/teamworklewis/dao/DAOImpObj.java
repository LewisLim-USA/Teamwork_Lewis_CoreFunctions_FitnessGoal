package com.example.teamworklewis.dao;

import com.example.teamworklewis.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpObj<T> implements DAOInterface<T> {

    @Override
    public String add(List<T> object) {
        List<T> profiles = object;
        //System.out.println(profiles.toString());
        try ( // Create an output stream for file array.dat
              ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C:\\Users\\LENOVO\\Desktop\\array.dat", false));
        ) {
            // Write arrays to the object output stream
            for(int i = 0; i < profiles.size(); i++) {
                //System.out.println(profiles.get(i));
                output.writeObject(profiles.get(i));
            }

        } catch (IOException ex) {

        }
        return "Add success";
    }

    @Override
    public T getObj(int id) {
        boolean status = false;
        T s = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\LENOVO\\Desktop\\array.dat"));

            while (true) {
                try {
                    s = (T) ois.readObject();
                    if (s instanceof User) {
                        if (((User) s).getId() == id) {
                            status = true;
                            break;
                        }
                    }
                } catch (Exception e) {
                    break;
                }
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(status) {
            return s;

        }
        return null;
        //System.out.println(((User)s).getId());

    }

    @Override
    public List<T> getAll() {
        List<T> readprofile = new ArrayList<>();
        try {
           // ClassLoader classLoader = getClass().getClassLoader();
         //   InputStream inputStream = classLoader.getResourceAsStream("C:\\Users\\LENOVO\\Desktop\\array.dat");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\LENOVO\\Desktop\\array.dat"));

            T s;
            while (true) {
                try {
                    s = (T)ois.readObject();
                    readprofile.add(s);
                } catch (Exception e) {
                    break;
                }
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readprofile;
    }


}
