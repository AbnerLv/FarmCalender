package com.pyp.farmcalender;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.pyp.farmcalender.ui.board.MessageBoardFragment;
import com.pyp.farmcalender.ui.record.RecordFragment;
import com.pyp.farmcalender.ui.setting.SettingFragment;


public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "MainActivity";

    private MessageBoardFragment messageBoardFragment;
    private RecordFragment recordFragment;
    private SettingFragment settingFragment;

    private View boardLayout;
    private View newsLayout;
    private View settingLayout;

    private ImageView messageImage;
    private ImageView newsImage;
    private ImageView settingImage;

    private TextView messageText;
    private TextView newsText;
    private TextView settingText;

    private FragmentManager fragmentManager;//用于对Fragment进行管理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        boardLayout = findViewById(R.id.message_board_layout);
        newsLayout = findViewById(R.id.other_layout);
        settingLayout = findViewById(R.id.setting_layout);

        messageImage = (ImageView) findViewById(R.id.message_board_image);
        newsImage = (ImageView) findViewById(R.id.other_image);
        settingImage = (ImageView) findViewById(R.id.setting_image);

        messageText = (TextView) findViewById(R.id.message_board_text);
        newsText = (TextView) findViewById(R.id.other_text);
        settingText = (TextView) findViewById(R.id.setting_text);

        boardLayout.setOnClickListener(this);
        newsLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.message_board_layout:
            setTabSelection(0);
            break;
        case R.id.other_layout:
            setTabSelection(1);
            break;
        case R.id.setting_layout:
            setTabSelection(2);
            break;
        default:
            break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     * 
     * @param index
     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        hideFragments(transaction);
        switch (index) {
        case 0:
            messageImage.setImageResource(R.mipmap.message_selected);
            messageText.setTextColor(Color.BLUE);
            Log.d(TAG, "taskManaFragment");
            if (messageBoardFragment == null) {
                messageBoardFragment = new MessageBoardFragment();
                transaction.add(R.id.content, messageBoardFragment);
            } else {
                messageBoardFragment.onResume();
                transaction.show(messageBoardFragment);
            }

            break;

        case 1:
            newsImage.setImageResource(R.mipmap.news_selected);
            newsText.setTextColor(Color.BLUE);
            if (recordFragment == null) {
                recordFragment = new RecordFragment();
                transaction.add(R.id.content, recordFragment);
            } else {
                recordFragment.onResume();
                transaction.show(recordFragment);
            }
            break;

        case 2:
        default:
            settingImage.setImageResource(R.mipmap.setting_selected);
            settingText.setTextColor(Color.BLUE);
            if (settingFragment == null) {
                settingFragment = new SettingFragment();
                transaction.add(R.id.content, settingFragment);
            } else {
                settingFragment.onResume();
                transaction.show(settingFragment);
            }
            break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        messageImage.setImageResource(R.mipmap.message_unselected);
        messageText.setTextColor(Color.parseColor("#82858b"));

        newsImage.setImageResource(R.mipmap.news_unselected);
        newsText.setTextColor(Color.parseColor("#82858b"));

        settingImage.setImageResource(R.mipmap.setting_unselected);
        settingText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     * 
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (recordFragment != null) {
            transaction.hide(recordFragment);
        }
        if (messageBoardFragment != null) {
            transaction.hide(messageBoardFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

}
