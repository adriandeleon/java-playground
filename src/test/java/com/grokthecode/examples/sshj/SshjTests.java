package com.grokthecode.examples.sshj;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.userauth.keyprovider.PKCS8KeyFile;
import net.schmizz.sshj.xfer.FileSystemFile;
import net.sf.expectit.Expect;
import net.sf.expectit.ExpectBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.security.Security;
import java.util.concurrent.TimeUnit;

import static net.sf.expectit.filter.Filters.removeColors;
import static net.sf.expectit.filter.Filters.removeNonPrintable;
import static net.sf.expectit.matcher.Matchers.contains;
import static net.sf.expectit.matcher.Matchers.regexp;

public class SshjTests {

    private static final Console con = System.console();


    @Test
    @Disabled
    void sshjConnectionTest() throws IOException {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());;

        SSHClient client = new SSHClient();

        client.addHostKeyVerifier(new PromiscuousVerifier());
        client.connect("18.212.148.26");

        PKCS8KeyFile keyFile = new PKCS8KeyFile();
        keyFile.init(new File("C:\\Users\\adria\\Backups\\bitnami-aws-031190398678.pem"));
        client.authPublickey("bitnami",keyFile);

        final Session session = client.startSession();
        final Session.Command cmd = session.exec("whoami");

        String response = IOUtils.readFully(cmd.getInputStream()).toString();
        cmd.join(10, TimeUnit.SECONDS);

        System.out.printf(response);

        session.close();
        client.disconnect();
    }

    @Test
    @Disabled
    void sshjConnectionWithExpectTest() throws IOException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());;

        final SSHClient sshClient = new SSHClient();

        sshClient.addHostKeyVerifier(new PromiscuousVerifier());
        sshClient.connect("18.212.148.26");

        final PKCS8KeyFile keyFile = new PKCS8KeyFile();
        keyFile.init(new File("C:\\Users\\adria\\Backups\\bitnami-aws-031190398678.pem"));
        sshClient.authPublickey("bitnami",keyFile);

        final Session session = sshClient.startSession();
        session.allocateDefaultPTY();

        final Session.Shell shell = session.startShell();

        final Expect expect = new ExpectBuilder()
                .withOutput(shell.getOutputStream())
                .withInputs(shell.getInputStream(), shell.getErrorStream())
                .withEchoInput(System.out)
                .withEchoOutput(System.err)
                .withInputFilters(removeColors(), removeNonPrintable())
                .withExceptionOnFailure()
                .withInfiniteTimeout()
                .build();

        final String shellPrompt = "bitnami@ip-172-31-45-172:~$";
        final String rootShellPrompt = "root@ip-172-31-45-172:/home/bitnami#";
        final String rootShellPromptBackups = "root@ip-172-31-45-172:/var/opt/gitlab/backups#";
        final String backupFile;
        try {
            expect.expect(contains(shellPrompt));

            //expect.sendLine("sudo gitlab-rake gitlab:backup:create");
            //expect.expect(contains(shellPrompt));

            expect.sendLine("rm *_gitlab_backup.tar");
            expect.expect(contains(shellPrompt));

            expect.sendLine("sudo su");
            expect.expect(contains(rootShellPrompt));

            expect.sendLine("cd /var/opt/gitlab/backups");
            expect.expect(contains(rootShellPromptBackups));

            //Get the latest file backup
            expect.sendLine("ls -t | head -n1");
            backupFile = expect.expect(regexp(".*_gitlab_backup\\.tar")).group();
            System.out.println(backupFile);

            expect.sendLine("cp " + backupFile + " /home/bitnami -a");
            expect.expect(contains(rootShellPromptBackups));

            expect.sendLine("cd /home/bitnami");
            expect.expect(contains(rootShellPrompt));

            expect.sendLine("chmod 644 " + backupFile);
            expect.expect(contains(rootShellPrompt));

            expect.sendLine("chown bitnami.bitnami " + backupFile);
            expect.expect(contains(rootShellPrompt));

            final String remoteFile = "/home/bitnami/" + backupFile;
            final String remoteDir = "C:\\Users\\adria\\Backups";

            sshClient.newSCPFileTransfer().download(remoteFile, new FileSystemFile(remoteDir));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            expect.close();
            session.close();
            sshClient.disconnect();
            sshClient.close();
        }
    }
}
