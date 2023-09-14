package ph.edu.auf.basicandroid

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ph.edu.auf.basicandroid.databinding.ActivityMainBinding
import ph.edu.auf.basicandroid.helpers.TipValues


class MainActivity : AppCompatActivity(), OnCheckedChangeListener, View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var selection: TipValues
    private var hasSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rgTip.setOnCheckedChangeListener(this)
        binding.btnCalculateTip.setOnClickListener(this)

    }

    private fun calculateTip(amount: Double): Double{
        return when(selection){
            (TipValues.TEN) -> (amount * .10)
            (TipValues.TWENTY) -> (amount * .20)
            (TipValues.THIRTY) -> (amount * .30)
            else -> 0.0
        }
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when(p1){
            (R.id.rb_ten) -> selection = TipValues.TEN
            (R.id.rb_twenty) -> selection = TipValues.TWENTY
            (R.id.rb_thirty) -> selection = TipValues.THIRTY
        }
        hasSelected = true
        binding.btnCalculateTip.isEnabled = true
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(p0: View?) {

        //HIDE KEYBOARD ON CLICK
        val imm = ContextCompat.getSystemService(p0!!.context, InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(p0.windowToken, 0)

        if(binding.edtTotalCharge.text.isNullOrEmpty()){
            binding.edtTotalCharge.error = "Total Charge required"
            return
        }

        if(hasSelected){
            val tip =  calculateTip(binding.edtTotalCharge.text.toString().toDouble())
            val roundOff = String.format("%.2f", tip)
            binding.txtTotalTip.text = "$${roundOff}"
        }
    }


}