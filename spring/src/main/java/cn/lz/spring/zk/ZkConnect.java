package cn.lz.spring.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class ZkConnect implements Watcher {

    @SuppressWarnings("unused")
    private void init() {
        // 王艺蒙的服务器
        String host = "123.56.68.78:2181,123.56.68.78:2182,123.56.68.78:2183";
        try {
            this.connect(host);
            this.list("");
            this.close();
        } catch (IOException e) {
            log.error("连接zk {} 失败", host, e);
        } catch (InterruptedException e) {
            log.error("CountDownLatch error", e);
            Thread.currentThread().interrupt();
        } catch (KeeperException e) {
            log.error("list zk error", e);
        }
    }

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private ZooKeeper zk;

    private static final int SESSION_TIMEOUT = 5000;

    public void connect(String host) throws IOException, InterruptedException {
        log.info("ready to connect zookeeper host: {}", host);
        zk = new ZooKeeper(host, SESSION_TIMEOUT, this);
        log.info("waiting to connect zookeeper");
        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }

    @SuppressWarnings("unused")
    public void joinGroup(String groupName, String memberName) throws KeeperException,
            InterruptedException {
        String path = "/" + groupName + "/" + memberName;
        String createdPath = zk.create(path, null/*data*/, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        log.info("created a path {}", createdPath);

    }

    @SuppressWarnings("unused")
    public void createGroup(String groupName) throws InterruptedException, KeeperException {
        String path = "/" + groupName;
        String createdPath = zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        log.info("create a group, createdPath:{}", createdPath);
    }

    public void list(String groupName) throws KeeperException,
            InterruptedException {
        String path = "/" + groupName;
        try {
            List<String> children = zk.getChildren(path, false);
            if (children.isEmpty()) {
                log.info("No members in group: {}", groupName);
                System.exit(1);
            }
            log.info("list members in group: {}", path);
            for (String child : children) {
                log.info("child: {}", child);
            }
        } catch (KeeperException.NoNodeException e) {
            log.info("group: {} does not exist", groupName);
            System.exit(1);

        }
    }

    public void close() throws InterruptedException {
        zk.close();
    }


}
