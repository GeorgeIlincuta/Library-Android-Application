package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Book book = new Book(1, "Gandire rapida, gandire lenta", "Daniel Kahneman", 752, "https://cdn4.libris.ro/img/pozeprod/59/1010/BE/572992-RGFL.jpg",
                "Daniel Kahneman ne poarta intr-o calatorie inovatoare prin ascunzisurile mintii si ne explica cele doua sisteme care conduc modul nostru de a gandi.",
                "Daniel Kahneman ne poarta intr-o calatorie inovatoare prin ascunzisurile mintii si ne explica cele doua sisteme care conduc modul nostru de a gandi. Sistemul 1 este rapid, intuitiv si emotional; Sistemul 2 este mai lent, mai deliberativ si mai logic. Kahneman prezinta capacitatile extraordinare si, deopotriva, erorile si biasurile  gandirii rapide si dezvaluie influenta omniprezenta a impresiilor intuitive asupra gandurilor si comportamentelor noastre. Impactul aversiunii fata de pierdere si al excesivei increderi de sine asupra strategiilor corporatiste, dificultatile previziunii a ceea ce ne va face fericiti in viitor, efectul profund al biasurilor cognitive asupra mai tuturor activitatilor noastre, de la jocul la bursa de valori pana la planificarea vacantei urmatoare  fiecare dintre acestea se pot intelege numai cunoscand modul in care cele doua sisteme ne modeleaza judecatile evaluative si deciziile noastre.\n" +
                        "\n" +
                        "Cartea ne ofera explicatii practice si lamuritoare ale modului in care se iau deciziile atat in afaceri, cat si in viata de zi cu zi aratandu-ne cum putem utiliza diferite tehnici pentru a ne feri de stangaciile mintale care adeseori ne vara in bucluc. Gandire rapida, gandire lenta va va schimba modul in care ganditi despre gandire.");

        setData(book);
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToReadList);

        bookImage = findViewById(R.id.bookImg);
    }
}