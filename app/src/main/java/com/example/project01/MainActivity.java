package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbEsTeh, cbEsJeruk, cbAyamGeprek, cbAyamPenyet;
    private CheckBox checkBoxMembership;
    private Button btnProcess;
    private TextView tvReceipt;

    private static final double ADMIN_ESTEH = 2000;
    private static final double ADMIN_ESJERUK = 2500;
    public static final double ADMIN_AYAMGEPREK = 3000;
    public static final double ADMIN_AYAMPENYET = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbEsTeh = findViewById(R.id.cbEsTeh);
        cbEsJeruk = findViewById(R.id.cbEsJeruk);
        cbAyamGeprek = findViewById(R.id.cbAyamGeprek);
        cbAyamPenyet = findViewById(R.id.cbAyamPenyet);
        checkBoxMembership = findViewById(R.id.checkBoxMembership);
        btnProcess = findViewById(R.id.btnProcess);
        tvReceipt = findViewById(R.id.tvReceipt);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processTransaction();
            }
        });
    }

    private void processTransaction() {
        StringBuilder items = new StringBuilder();
        double totalBeforeAdmin = 0;
        int quantity = 0;

        if (cbEsTeh.isChecked()) {
            double price = 5000;
            double admin = ADMIN_ESTEH;
            items.append(String.format("Es Teh - %s\nBiaya Admin: %s\n", formatCurrency(price), formatCurrency(admin)));
            totalBeforeAdmin += price + admin;
            quantity++;
        }
        if (cbEsJeruk.isChecked()) {
            double price = 6000;
            double admin = ADMIN_ESJERUK;
            items.append(String.format("Es Jeruk - %s\nBiaya Admin: %s\n", formatCurrency(price), formatCurrency(admin)));
            totalBeforeAdmin += price + admin;
            quantity++;
        }
        if (cbAyamGeprek.isChecked()) {
            double price = 20000;
            double admin = ADMIN_AYAMGEPREK;
            items.append(String.format("Ayam Geprek - %s\nBiaya Admin: %s\n", formatCurrency(price), formatCurrency(admin)));
            totalBeforeAdmin += price + admin;
            quantity++;
        }
        if (cbAyamPenyet.isChecked()) {
            double price = 20000;
            double admin = ADMIN_AYAMPENYET;
            items.append(String.format("Ayam Penyet - %s\nBiaya Admin: %s\n", formatCurrency(price), formatCurrency(admin)));
            totalBeforeAdmin += price + admin;
            quantity++;
        }
        if (checkBoxMembership.isChecked()) {
            totalBeforeAdmin *= 0.95;
        }

        double totalAfterAdmin = totalBeforeAdmin;

        String receipt = "Nama Barang:\n" + items.toString() + "\n"
                + "Banyak Barang: " + quantity + "\n"
                + "Total Harga Barang: " + formatCurrency(totalBeforeAdmin) + "\n"
                + "Diskon: " + (checkBoxMembership.isChecked() ? "5%" : "0%") + "\n"
                + "Total Bayar: " + formatCurrency(totalAfterAdmin);

        tvReceipt.setText(receipt);
        setTitle("Aplikasi Kasir Sederhana - Struk Transaksi");
    }

    private String formatCurrency(double amount) {
        return String.format("Rp %,.2f", amount);
    }
}
