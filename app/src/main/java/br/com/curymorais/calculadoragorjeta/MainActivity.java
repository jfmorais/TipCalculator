package br.com.curymorais.calculadoragorjeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmmount = 0.0;
    private double percent = 0.10;

    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = (TextView) findViewById(R.id.totalTextView);
        percentTextView = (TextView) findViewById(R.id.porcentagemTextView);
        tipTextView = (TextView) findViewById(R.id.gorjetaTextView);
        totalextView = (TextView) findViewById(R.id.totalTextView);
        tipTextView.setText(currencyFormat.format(0));
        totalextView.setText(currencyFormat.format(0));


        EditText amountEditText = (EditText) findViewById(R.id.quantidadeEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.porcentagemSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    private void calculate(){
        percentTextView.setText(percentFormat.format(percent));

        double tip = billAmmount * percent;
        double total = billAmmount + tip;

        tipTextView.setText(currencyFormat.format(tip));
        totalextView.setText(currencyFormat.format(total));
    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            percent = i / 100.0;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{
                billAmmount = Double.parseDouble(charSequence.toString()) / 100.0;
                amountTextView.setText(currencyFormat.format(billAmmount));
            }catch (NumberFormatException e){
                amountTextView.setText("");
                billAmmount = 0.0;
            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
