package com.grokthecode.examples.sshj;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.userauth.keyprovider.PKCS8KeyFile;
import net.sf.expectit.Expect;
import net.sf.expectit.ExpectBuilder;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.security.Security;
import java.util.concurrent.TimeUnit;

import static net.sf.expectit.filter.Filters.removeColors;
import static net.sf.expectit.filter.Filters.removeNonPrintable;
import static net.sf.expectit.matcher.Matchers.contains;

public class SshjTests {

    private static final Console con = System.console();


    @Test
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
    void sshjConnectionWithExpectTest() throws IOException {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());;

        final SSHClient client = new SSHClient();
        client.addHostKeyVerifier(new PromiscuousVerifier());
        client.connect("18.212.148.26");

        final PKCS8KeyFile keyFile = new PKCS8KeyFile();
        keyFile.init(new File("C:\\Users\\adria\\Backups\\bitnami-aws-031190398678.pem"));
        client.authPublickey("bitnami",keyFile);


        final Session session = client.startSession();
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
        try {
            expect.expect(contains(shellPrompt));
            expect.sendLine("sudo gitlab-rake gitlab:backup:create");
            expect.expect(contains(shellPrompt));
            expect.sendLine("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            expect.close();
            session.close();
            client.close();
        }
    }
}
