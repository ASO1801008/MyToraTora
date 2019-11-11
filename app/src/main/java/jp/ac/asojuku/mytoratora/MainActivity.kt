package jp.ac.asojuku.mytoratora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onResume(){
        super.onResume()
        baachan.setOnClickListener{onToratoraButtonTapped(it);}
        kato.setOnClickListener{onToratoraButtonTapped(it);}
        tora.setOnClickListener{onToratoraButtonTapped(it);}
    }




    fun onToratoraButtonTapped(view: View?){
        val intent = Intent(this,ResultActivity::class.java);
        intent.putExtra("MY_HAND",view?.id);


        startActivity(intent);


    }
}
