package com.github.caelis.arse.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.caelis.arse.android.Arse;
import com.github.caelis.arse.example.core.Example1;
import com.github.caelis.violake.android.ext.GetChecked;
import com.github.caelis.violake.android.ext.GetClick;
import com.github.caelis.violake.android.ext.SetChecked;
import com.github.caelis.violake.android.ext.SetText;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.processors.BehaviorProcessor;
import java8.util.function.Consumer;

public class MainActivity extends AppCompatActivity {

    private final Example1 data = new Example1();

    private final BehaviorProcessor<Integer> clickCounts = BehaviorProcessor.createDefault(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button button = findViewById(R.id.the_button);
        TextView text = findViewById(R.id.the_text_view);
        CheckBox box = findViewById(R.id.checkBox);

        Arse.get().apply(GetClick.get(), button, data::incrementClickCount);
        Arse.get().apply(SetText.get(), text, data.message());
        Arse.get().apply(SetChecked.get(), box, data.isEvenClickCount());
        Arse.get().apply(GetChecked.get(), box, (Consumer<Boolean>) isEven -> data.setEventClickCount(isEven));
    }

}
