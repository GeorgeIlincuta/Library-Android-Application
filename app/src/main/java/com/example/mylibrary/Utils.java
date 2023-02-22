package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";
    private static Utils instance;
    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;

    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (null == getAllBooks()) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getFavoriteBooks()) {
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "Gandire rapida, gandire lenta", "Daniel Kahneman", 752, "https://cdn4.libris.ro/img/pozeprod/59/1010/BE/572992-RGFL.jpg",
                "Daniel Kahneman ne poarta intr-o calatorie inovatoare prin ascunzisurile mintii si ne explica cele doua sisteme care conduc modul nostru de a gandi.",
                "Daniel Kahneman ne poarta intr-o calatorie inovatoare prin ascunzisurile mintii si ne explica cele doua sisteme care conduc modul nostru de a gandi. Sistemul 1 este rapid, intuitiv si emotional; Sistemul 2 este mai lent, mai deliberativ si mai logic. Kahneman prezinta capacitatile extraordinare si, deopotriva, erorile si biasurile  gandirii rapide si dezvaluie influenta omniprezenta a impresiilor intuitive asupra gandurilor si comportamentelor noastre. Impactul aversiunii fata de pierdere si al excesivei increderi de sine asupra strategiilor corporatiste, dificultatile previziunii a ceea ce ne va face fericiti in viitor, efectul profund al biasurilor cognitive asupra mai tuturor activitatilor noastre, de la jocul la bursa de valori pana la planificarea vacantei urmatoare  fiecare dintre acestea se pot intelege numai cunoscand modul in care cele doua sisteme ne modeleaza judecatile evaluative si deciziile noastre.\n" +
                        "\n" +
                        "Cartea ne ofera explicatii practice si lamuritoare ale modului in care se iau deciziile atat in afaceri, cat si in viata de zi cu zi aratandu-ne cum putem utiliza diferite tehnici pentru a ne feri de stangaciile mintale care adeseori ne vara in bucluc. Gandire rapida, gandire lenta va va schimba modul in care ganditi despre gandire."));
        books.add(new Book(2, "O pereche de pantofi: finantele si contabilitatea afacerii", "Tinca Celnicu", 296, "https://cdn4.libris.ro/img/pozeprod/59/1000/4F/1068919-MIBX.jpg",
                "In facultate, primul lucru pe care voiam sa-l fac cand deschideam o carte de finante sau de contabilitate era s-o inchid repede la loc.",
                " In facultate, primul lucru pe care voiam sa-l fac cand deschideam o carte de finante sau de contabilitate era s-o inchid repede la loc. Dupa ce am descoperit cu adevarat acest domeniu si am inceput sa-l stapanesc, mi-am dat seama ca nu este chiar asa de fioros. Am inteles ca finantele si contabilitatea inseamna cateva instrumente practice si elegante, care aduc liniste si claritate in afaceri. \n" +
                        "    Dupa aproape 20 de ani de practica de business si dupa ce am curtat indelung subiectul, am reusit sa pun toate notiunile acestea abstracte si aparent complicate intr-o carte care trateaza finantele si contabilitatea afacerii pe intelesul tuturor.\n" +
                        "    Cartea se adreseaza  „nefinantistilor” din business, viitorilor si actualilor manageri, antreprenorilor, dar si studentilor si tuturor celor care vor sa-si struneasca mai bine afacerea sau cariera. (Tinca Celnicu)"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();
    }

    public static synchronized Utils getInstance(Context context) {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS, null), type);
        return books;
    }

    public Book getBookById(int id ) {
        ArrayList<Book> books = getAllBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public  boolean addToWantToRead (Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public  boolean addToFavorite (Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead (Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return  true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWantToRead (Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return  true;
                    }
                }
            }
        }
        return false;
    }

    public  boolean removeFromCurrentlyReading (Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return  true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavorites (Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return  true;
                    }
                }
            }
        }
        return false;
    }
}
