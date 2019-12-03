package id.co.imastudio.portalberita;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {

WebView webV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_web_view, container, false);

        webV = (WebView)v.findViewById(R.id.wView);
        String url ="http://unucirebon.ac.id/";
        webV.loadUrl(url);
        webV.setWebViewClient(new WebViewClient());
        webV.getSettings().setJavaScriptEnabled(true);

        return v;
    }

}
