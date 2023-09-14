package ph.edu.auf.basicandroid

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ph.edu.auf.basicandroid.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnCheckedChangeListener, View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var selection: Int = -1
    private var hasSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.rgTip.setOnCheckedChangeListener(this)
        //binding.btnCalculateTip.setOnClickListener(this)
        binding.btnCalculateTip.setOnClickListener {
            

            if(binding.edtTotalCharge.text.isNullOrEmpty()){
                binding.edtTotalCharge.error = "Total Charge required"
                return@setOnClickListener
            }

            if(hasSelected){
                binding.txtTotalTip.text = "${calculateTip(binding.edtTotalCharge.text.toString().toDouble())}"
            }

        }

    }

    private fun calculateTip(amount: Double): Double{
        return when(selection){
            (1) -> (amount * .10)
            (2) -> (amount * .20)
            (3) -> (amount * .30)
            else -> 0.0
        }
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when(p1){
            (R.id.rb_ten) -> selection = 1
            (R.id.rb_twenty) -> selection = 2
            (R.id.rb_thirty) -> selection = 3
        }
        hasSelected = true
        binding.btnCalculateTip.isEnabled = true
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}