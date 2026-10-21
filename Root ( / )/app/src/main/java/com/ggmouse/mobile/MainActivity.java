package com.ggmouse.mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int OVERLAY_REQUEST = 501;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(28, 36, 28, 24);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setBackgroundColor(0xFF101820);

        TextView title = new TextView(this);
        title.setText("GG MOUSE MOBILE");
        title.setTextSize(26);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);
        root.addView(title, matchWrap());

        TextView status = new TextView(this);
        status.setText("\nVersão inicial • Android nativo\n\nEsta versão testa a interface e o ícone flutuante. Ainda não injeta toques nem mapeia teclas no Free Fire.");
        status.setTextSize(16);
        status.setTextColor(0xFFE0E6EA);
        root.addView(status, matchWrap());

        Button overlay = new Button(this);
        overlay.setText("ATIVAR ÍCONE FLUTUANTE");
        overlay.setOnClickListener(v -> enableOverlay());
        root.addView(overlay, matchWrap());

        Button games = new Button(this);
        games.setText("MEUS JOGOS");
        games.setOnClickListener(v -> Toast.makeText(this, "A lista de jogos será adicionada na próxima etapa.", Toast.LENGTH_LONG).show());
        root.addView(games, matchWrap());

        Button editor = new Button(this);
        editor.setText("EDITOR DE CONTROLES");
        editor.setOnClickListener(v -> Toast.makeText(this, "O editor de mapeamento será desenvolvido após o teste do overlay.", Toast.LENGTH_LONG).show());
        root.addView(editor, matchWrap());

        setContentView(root);
    }

    private LinearLayout.LayoutParams matchWrap() {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, 18, 0, 0);
        return p;
    }

    private void enableOverlay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent i = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
            startActivityForResult(i, OVERLAY_REQUEST);
        } else {
            startService(new Intent(this, FloatingService.class));
            Toast.makeText(this, "Ícone flutuante ativado.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_REQUEST) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(this)) {
                startService(new Intent(this, FloatingService.class));
            } else {
                Toast.makeText(this, "A permissão de sobreposição não foi concedida.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
