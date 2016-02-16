package cn.campusapp.anoledialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.campusapp.dialog.AnoleAlertDialog;
import cn.campusapp.dialog.AnoleDialog;
import cn.campusapp.dialog.AnoleProgressDialog;

public class MainActivity extends AppCompatActivity {
    private static final String INFO = "补充：据博客园园友http://www.cnblogs.com/longware/发现，直接进入Android Studio安装目录，运行bin\\studio.bat，发现错误\n" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnoleAlertDialog dialog = AnoleAlertDialog.build(MainActivity.this, AnoleDialog.DialogType.TEXT)
                        .setTouchOutsideDismiss(true)
                        .setText(INFO)
                        .setTextColor(R.color.blue)
                        .setRightBtn("更新", new AnoleAlertDialog.OnAnoleBtnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                        .setLeftBtn("取消", new AnoleAlertDialog.OnAnoleBtnClickListener() {
                            @Override
                            public void onClick(Dialog dialog) {
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                dialog.show();
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnoleProgressDialog.build(MainActivity.this).setLoadingText("hahah ").show();
            }
        });
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
