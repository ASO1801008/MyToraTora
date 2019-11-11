package jp.ac.asojuku.mytoratora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import jp.ac.asojuku.mytoratora.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    val baachan=0; val kato = 1; val tora=2;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }

    override fun onResume(){
        super.onResume();
        val id = intent.getIntExtra("MY_HAND",0);
        val myHand:Int;
        myHand = when(id){
            R.id.tora->{myHandImage.setImageResource(R.drawable.tora);tora;};
            R.id.baachan->{myHandImage.setImageResource(R.drawable.baachan);baachan;};
            R.id.kato->{myHandImage.setImageResource(R.drawable.kato);kato;};
            else-> baachan;
        }
        val comHand = (Math.random() * 3).toInt();
        when(comHand){
            tora -> {comHandImage.setImageResource(R.drawable.com_tora);}
            baachan -> {comHandImage.setImageResource(R.drawable.com_baachan);}
            kato -> {comHandImage.setImageResource(R.drawable.com_kato);}
            else -> tora;
        }

        val gameResult = (comHand - myHand + 3) % 3;
        when(gameResult){
            0 -> resultLabel.setText("あいこです");
            1 -> resultLabel.setText("あなたの勝ちです");
            2 -> resultLabel.setText("コンピューターの勝ちです");
        }

        backButton.setOnClickListener{this.finish()};
    }

    private fun savaData(myHand:Int,comHand:Int,gameResult:Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val gameConunt = pref.getInt("GAME_COUNT",0);
        val winningStreakCount = pref.getInt("WINNING_STREAK_COUNT",0);
        val lastComHand = pref.getInt("LAST_COM_HAND",0);
        val lastGameResult = pref.getInt("GAME_RESULT",-1);
        val editWinningStreakCount = when{
            (lastGameResult == 2 && gameResult == 2) -> (winningStreakCount +1);
            else -> 0;
        }
        val editor = pref.edit();
        //editorのメソッドをメソッドチェーンで呼び出し
        editor.putInt("GAVE_COUNT",gameConunt+1)
            .putInt("WINNING_STREAK_COUNT",editWinningStreakCount)
            .putInt("LAST_MY_HAND",myHand)
            .putInt("LAST_COM_HAND",comHand)
            .putInt("BEFORE_LAST_COM_HAND",lastComHand)
            .putInt("GAVE_RESULT",gameResult)
            .apply();
    }
    private fun getHand():Int{
        val hand = (Math.random() * 3).toInt();//0~0.9までの乱数を３倍して整数にすると0か1か2

        return hand;
    }

}