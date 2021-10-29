package com.example.demo.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * @Author Zhu Jiayi
 * @Date 2021/8/9 16:07
 */
public class Application {
    public static void main(String[] args) throws Exception {
        File file = new File("");
        RandomAccessFile randomAccessFile = new RandomAccessFile("", "rw");
        final FileDescriptor fd = randomAccessFile.getFD();
        final FileChannel channel = randomAccessFile.getChannel();
        channel.truncate(1024);
        ByteBuffer buf = ByteBuffer.allocate(48);

        channel.read(buf);
        buf.flip();
        CharBuffer charBuffer = CharBuffer.allocate(48);

        LongBuffer longBuffer = LongBuffer.allocate(48);

        charBuffer.position(0);
        charBuffer.rewind();
        charBuffer.clear();
        charBuffer.compact();
        charBuffer.mark();
        charBuffer.reset();

        Selector selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(2));
        socketChannel.configureBlocking(false);
        SelectionKey selectionKey = socketChannel.register(selector, 0);
        int readyOps = selectionKey.readyOps();
        selectionKey.isReadable();
        final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        selector.selectedKeys().forEach(s -> {
            s.channel();
            selector.wakeup();
        });

        //FileChannel fileChannel=

        InputStream inputStream=new FileInputStream("");
        inputStream.read();

    }

    private void serverSocket() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(999));
        serverSocketChannel.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            serverSocketChannel.close();
        }
    }

    private void datagramChannel() throws Exception {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress(99));
        datagramChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(48);
        datagramChannel.receive(buffer);


    }

    private void pipe() throws Exception {
        Pipe pipe = Pipe.open();
        final Pipe.SinkChannel sink = pipe.sink();
        sink.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        while (byteBuffer.hasRemaining()) {
            sink.write(byteBuffer);
        }


        final Pipe.SourceChannel source = pipe.source();
        int readCount= source.read(byteBuffer);
    }

    private void path(){
        final Path path = Paths.get("");

    }
}
