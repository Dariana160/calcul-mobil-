package com.fashionmirror.app;

import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private LinearLayout catalogContainer;
    private final List<ClothingItem> clothes = new ArrayList<>();

    private static final int DARK = Color.rgb(73, 52, 63);
    private static final int MUTED = Color.rgb(128, 109, 118);
    private static final int CARD = Color.WHITE;
    private static final int ACCENT = Color.rgb(174, 119, 143);

    private static class ClothingItem {
        String name;
        String category;
        String emoji;
        String price;

        ClothingItem(String name, String category,
                     String emoji, String price) {
            this.name = name;
            this.category = category;
            this.emoji = emoji;
            this.price = price;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        catalogContainer = findViewById(R.id.catalogContainer);

        addClothes();

        findViewById(R.id.btnAll).setOnClickListener(v -> showClothes("Toate"));
        findViewById(R.id.btnTops).setOnClickListener(v -> showClothes("Bluze"));
        findViewById(R.id.btnPants).setOnClickListener(v -> showClothes("Pantaloni"));
        findViewById(R.id.btnDresses).setOnClickListener(v -> showClothes("Rochii"));
        findViewById(R.id.btnShoes).setOnClickListener(v -> showClothes("Încălțăminte"));
        findViewById(R.id.btnAccessories).setOnClickListener(v -> showClothes("Accesorii"));

        showClothes("Toate");
    }

    private void addClothes() {
        clothes.add(new ClothingItem("Tricou basic", "Bluze", "👕", "49 lei"));
        clothes.add(new ClothingItem("Pulover bej", "Bluze", "🧥", "119 lei"));
        clothes.add(new ClothingItem("Hanorac casual", "Bluze", "👚", "139 lei"));

        clothes.add(new ClothingItem("Blugi clasici", "Pantaloni", "👖", "149 lei"));
        clothes.add(new ClothingItem("Pantaloni eleganți", "Pantaloni", "👖", "129 lei"));
        clothes.add(new ClothingItem("Fustă plisată", "Pantaloni", "👗", "99 lei"));

        clothes.add(new ClothingItem("Rochie neagră", "Rochii", "👗", "179 lei"));
        clothes.add(new ClothingItem("Rochie de vară", "Rochii", "👗", "149 lei"));

        clothes.add(new ClothingItem("Sneakers albi", "Încălțăminte", "👟", "229 lei"));
        clothes.add(new ClothingItem("Cizme elegante", "Încălțăminte", "👢", "259 lei"));

        clothes.add(new ClothingItem("Geantă de umăr", "Accesorii", "👜", "159 lei"));
        clothes.add(new ClothingItem("Ochelari de soare", "Accesorii", "🕶️", "79 lei"));
        clothes.add(new ClothingItem("Șapcă", "Accesorii", "🧢", "59 lei"));
    }

    private void showClothes(String category) {
        catalogContainer.removeAllViews();

        int count = 0;

        for (ClothingItem item : clothes) {
            if (!category.equals("Toate")
                    && !item.category.equals(category)) {
                continue;
            }

            catalogContainer.addView(createClothingCard(item));
            count++;
        }

        if (count == 0) {
            TextView empty = new TextView(this);
            empty.setText("Nu există produse în această categorie.");
            catalogContainer.addView(empty);
        }
    }

    private View createClothingCard(ClothingItem item) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(16, 14, 16, 14);

        GradientDrawable background = new GradientDrawable();
        background.setColor(CARD);
        background.setCornerRadius(24);
        background.setStroke(1, Color.rgb(239, 225, 231));
        card.setBackground(background);

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        cardParams.bottomMargin = 12;
        card.setLayoutParams(cardParams);

        TextView emoji = new TextView(this);
        emoji.setText(item.emoji);
        emoji.setTextSize(38);
        emoji.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams emojiParams =
                new LinearLayout.LayoutParams(76, 76);
        emojiParams.rightMargin = 14;
        card.addView(emoji, emojiParams);

        LinearLayout details = new LinearLayout(this);
        details.setOrientation(LinearLayout.VERTICAL);

        TextView name = new TextView(this);
        name.setText(item.name);
        name.setTextColor(DARK);
        name.setTextSize(17);
        name.setTypeface(null, Typeface.BOLD);

        TextView category = new TextView(this);
        category.setText(item.category);
        category.setTextColor(MUTED);
        category.setTextSize(13);

        TextView price = new TextView(this);
        price.setText(item.price);
        price.setTextColor(ACCENT);
        price.setTextSize(16);
        price.setTypeface(null, Typeface.BOLD);

        details.addView(name);
        details.addView(category);
        details.addView(price);

        card.addView(details);

        card.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        item.name + " — " + item.price,
                        Toast.LENGTH_SHORT
                ).show()
        );

        return card;
    }
}
