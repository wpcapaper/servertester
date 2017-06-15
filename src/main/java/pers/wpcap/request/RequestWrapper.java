package pers.wpcap.request;

import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA on 2017/6/14.
 * ClassName: RequestWrapper
 * Created by haisong
 * Description:
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private long id;

    public RequestWrapper(Long requestId, HttpServletRequest request) {
        super(request);
        this.id = requestId;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            private TeeInputStream teeInputStream =
                    new TeeInputStream(RequestWrapper.super.getInputStream(),
                            byteArrayOutputStream);

            @Override
            public int read() throws IOException {
                return teeInputStream.read();
            }
        };
    }

    public byte[] toByteArray() {
        return byteArrayOutputStream.toByteArray();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
