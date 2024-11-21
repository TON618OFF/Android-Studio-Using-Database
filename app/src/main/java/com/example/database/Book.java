package com.example.database;

import android.icu.text.CaseMap;

public class Book {
    private int ID;
    private String Title;
    private String Author;

    public Book(int ID_Book, String Title_Book, String Author_Book){
        this.ID = ID_Book;
        this.Title = Title_Book;
        this.Author = Author_Book;
    }

    public int getID_Book(){
        return ID;
    }

    public void setID_Book(int ID_Book){
        ID = ID_Book;
    }

    public String getName_Book(){
        return Title;
    }

    public void setName_Book(String Title_Book){
        Title = Title_Book;
    }

    public String getAuthor_Book(){
        return Author;
    }

    public void setAuthor_Book(String Author_Book){
        Author = Author_Book;
    }


}
