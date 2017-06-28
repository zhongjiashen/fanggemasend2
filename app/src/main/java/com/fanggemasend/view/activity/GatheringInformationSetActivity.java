package com.fanggemasend.view.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fanggema.fanggemasend.R;
import com.fanggemasend.dragger.component.DaggerMainComponent;
import com.fanggemasend.dragger.module.MainModule;
import com.fanggemasend.presenter.GatheringInformationSetPresenter;
import com.fanggemasend.viewbar.TitleBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 收款信息设置
 * Created by 1363655717 on 2017/4/26.
 */

public class GatheringInformationSetActivity extends BaseActivity {
    @BindView(R.id.titler)
    TitleBar titler;
    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.yhkh_et)
    EditText yhkhEt;
    @BindView(R.id.bank_name)
    TextView bankName;
    @BindView(R.id.zfbname_et)
    EditText zfbnameEt;
    @BindView(R.id.zfbzh_et)
    EditText zfbzhEt;


    @Inject
    GatheringInformationSetPresenter presenter;
    @Override
    public void update(int state, Object bean) {
        Intent intent = new Intent(this, RealNameAuthenticationStateActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int layout() {
        return R.layout.activity_gathering_information_set;
    }

    @Override
    protected void initview() {
        DaggerMainComponent.builder().mainModule(new MainModule(this, this)).build().inject(this);
        titler.setTitletext("收款信息设置");
        titler.setleft(this, 0);
    }

    @OnClick({R.id.bt_ok, R.id.khh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_ok:
                String[] strings=new String[]{nameEt.getText().toString()
                        ,yhkhEt.getText().toString(),bankName.getText().toString()
                        ,zfbnameEt.getText().toString(),zfbzhEt.getText().toString()};
                presenter.SubmitInformation(strings);
                break;
            case R.id.khh:
                final Dialog dialog = new Dialog(
                        this, R.style.mydialogstyle);
                final String[] data = new String[]{"中国银行", "农业银行", "建设银行", "中国邮政", "工商银行",
                        "光大银行", "兴业银行", "交通银行", "民生银行", "浦发银行", "平安银行", "广发银行", "招商银行",
                        "中信银行"};
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.choice_bank_dialog, null);
                ListView listView = (ListView) layout.findViewById(R.id.listView);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        bankName.setText(data[position]);
                        dialog.dismiss();
                    }
                });
                listView.setAdapter(new BaseAdapter() {

                    @Override
                    public int getCount() {
                        return data.length;
                    }

                    @Override
                    public Object getItem(int position) {


                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        ViewHolder holder;
                        if (convertView == null) {
                            holder = new ViewHolder();
                            convertView = LayoutInflater.from(GatheringInformationSetActivity.this).inflate(R.layout.choice_bank_dialog_item, null);
                            holder.name = (TextView) convertView.findViewById(R.id.bank_name);
                            holder.check = (ImageView) convertView.findViewById(R.id.gon_imag);
                            convertView.setTag(holder);
                        } else {
                            holder = (ViewHolder) convertView.getTag();
                        }
                        holder.name.setText(data[position]);
                        holder.check.setVisibility(View.GONE);
                        if (bankName.getText().toString().equals(data[position])) {
                            holder.check.setVisibility(View.VISIBLE);
                        }
                        return convertView;
                    }
                });
                dialog.setContentView(layout);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
                break;
        }
    }
    private static class ViewHolder {

        /**
         * 经营品类
         */
        ImageView check;
        /**
         * 订单状态
         */
        TextView name;

    }
}
