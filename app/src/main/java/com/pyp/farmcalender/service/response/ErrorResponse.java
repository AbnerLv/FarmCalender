package com.pyp.farmcalender.service.response;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by lvzhenbin on 2015/10/9.
 */
public class ErrorResponse implements Response.ErrorListener {

    private Context context;

    public ErrorResponse(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Toast.makeText(context, volleyError.getMessage(), Toast.LENGTH_LONG).show();
    }


}
