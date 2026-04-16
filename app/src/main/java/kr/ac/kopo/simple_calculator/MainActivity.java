package kr.ac.kopo.simple_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textResult;
    String num1, num2;
    Double result;

    // 숫자 버튼을 위한 배열 (10개)

    // XML의 숫자 버튼 ID를 담은 배열
    Integer[] numBtnIds = {R.id.BtnNum0, R.id.BtnNum1, R.id.BtnNum2, R.id.BtnNum3, R.id.BtnNum4,
            R.id.BtnNum5, R.id.BtnNum6, R.id.BtnNum7, R.id.BtnNum8, R.id.BtnNum9
    };
    Button[] numButtons = new Button[numBtnIds.length];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간이 계산기");

        // 위젯 연결
        edit1 = findViewById(R.id.Edit1);
        edit2 = findViewById(R.id.Edit2);

        btnAdd = findViewById(R.id.BtnAdd);
        btnSub = findViewById(R.id.BtnSub);
        btnMul = findViewById(R.id.BtnMul);
        btnDiv = findViewById(R.id.BtnDiv);

        textResult = findViewById(R.id.TextResult);

        // --- 1. 사칙연산 버튼 이벤트 설정 ---
        View.OnClickListener clisten = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                if (num1.trim().equals("") || num2.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "입력값이 없습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 어떤 버튼이 눌렸는지 ID로 구분
                int id = v.getId();
                if (id == R.id.BtnAdd)
                {
                    result = Double.parseDouble(num1) + Double.parseDouble(num2);
                } else if (id == R.id.BtnSub)
                {
                    result = Double.parseDouble(num1) - Double.parseDouble(num2);
                } else if (id == R.id.BtnMul)
                {
                    result = Double.parseDouble(num1) * Double.parseDouble(num2);
                } else if (id == R.id.BtnDiv)
                {
                    if (Double.parseDouble(num2) == 0)
                    {
                        Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = Double.parseDouble(num1) / Double.parseDouble(num2);
                }
                textResult.setText("계산 결과 : " + result);
            }
        };

        btnAdd.setOnClickListener(clisten);
        btnSub.setOnClickListener(clisten);
        btnMul.setOnClickListener(clisten);
        btnDiv.setOnClickListener(clisten);

        // --- 2. 숫자 버튼 배열 처리 ---
        for (int i = 0; i < numBtnIds.length; i++)
        {
            final int index = i; // 내부 클래스에서 사용하기 위해 상수로 선언
            numButtons[i] = findViewById(numBtnIds[i]);

            numButtons[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // 현재 포커스된 EditText에 숫자를 추가
                    if (edit1.isFocused())
                    {
                        num1 = edit1.getText().toString() + index;
                        edit1.setText(num1);
                    } else if (edit2.isFocused())
                    {
                        num2 = edit2.getText().toString() + index;
                        edit2.setText(num2);
                    } else
                    {
                        Toast.makeText(getApplicationContext(), "먼저 입력창을 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}