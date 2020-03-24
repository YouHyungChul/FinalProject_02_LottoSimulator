package kr.co.tjoeun.finalproject_02_lottosimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.co.tjoeun.finalproject_02_lottosimulator.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding = null;
    List<TextView> winNumTxtList = new ArrayList<>();
    int[] winLottoNumArr = new int[6];
    int bonusNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        binding.buyOneLottoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                당첨번호를 생성 => 텍스트뷰에 반영할건데 너무 기니까 setValues에 쓰고 여기에는 실행만
                makeWinLottoNum();
            }
        });

    }

    @Override
    public void setValues() {
        winNumTxtList.add(binding.sinLottoNumTxt01);
        winNumTxtList.add(binding.sinLottoNumTxt02);
        winNumTxtList.add(binding.sinLottoNumTxt03);
        winNumTxtList.add(binding.sinLottoNumTxt04);
        winNumTxtList.add(binding.sinLottoNumTxt05);
        winNumTxtList.add(binding.sinLottoNumTxt06);
    }
        void makeWinLottoNum() {
//            6개의 숫자 (배열) + 보너스 번호 1개 int 변수
//         => 이 함수에서만이 아니라, 다른곳에서도 쓸 예정.
//         => 당첨 등수 확인때도 사용해야한다 =>> 멤버 변수로 만들자.

//        당첨번호 + 보너스번호를 모두 0으로 초기화. --> 이미 뽑은 번호가 있다면 모두 날리자의 의미.
        for (int i=0; i<winLottoNumArr.length; i++){
            winLottoNumArr[i] = 0;
        }
        bonusNum =0;

//        로또번호 6개 생성.
//        1~45여야 하고 + 중복은 허용되지 않아야한다.
        for(int i= 0; i<winLottoNumArr.length; i++){
//            1~45의 숫자를 랜덤으로 뽑고
//            중복이 아니라면 => 당첨번호로 선정.
//            만약 중복이라면? => 다시 뽑자 => 중복이 아닐때까지 다시 뽑자~
            while(true) {
//                0<= Math.random() <1 -> 45를 곱하면 => 0<= Math.random() <45 ->> 여기다 1을 더하면 1<= Math.random() <46
//                1~45의 정수를 랜덤으로 뽑아서 임시저장.
                int randomNum = (int) (Math.random()*45+1);

//                중복검사? 당첨번호 전부와 randomNum을 비교
//                하나라도 같으면 탈락이다.
                boolean isDulplOk = true; // 중복검사 변수
                for(int winNum : winLottoNumArr){
                    if(winNum == randomNum){
                        isDulplOk = false;
                        break;
                    }
                }
                if(isDulplOk) {
                    winLottoNumArr[i] = randomNum;
                    Log.i("당첨번호", randomNum+"");
                    break;
//                        무한반복 탈출 --> 왜?? 번호 찾았으니까
                }
            }

        }
//        6개의 당첨번호를 작은 숫자부터 정렬.
        Arrays.sort(winLottoNumArr);
        for (int num : winLottoNumArr){
            Log.i("정렬된숫자", num+"");
            for (int i = 0; i < winLottoNumArr.length; i++) {
//                setText에다가 배열 넣으면 터진다==> 그래서 강제로 ""붙여서 String화 
                winNumTxtList.get(i).setText(winLottoNumArr[i]+"");
            }
        }

        }

    }
