# GG Mouse Mobile — projeto inicial Java nativo

Este projeto Android nativo contém:
- tela inicial Java;
- solicitação de permissão para aparecer sobre outros apps;
- bolha flutuante móvel de teste;
- GitHub Actions para compilar automaticamente o APK de depuração.

## Limitação desta versão
Isto ainda **não** mapeia teclado/mouse nem injeta toques no Free Fire. Shizuku/ADB não garantem essa capacidade sozinhos. O overlay é apenas a primeira etapa de teste.

## Compilar no GitHub pelo celular
1. Crie um repositório vazio no GitHub chamado `gg-mouse-mobile`.
2. No navegador do celular, abra o repositório e use **Add file → Upload files**.
3. Envie o conteúdo deste ZIP (extraia antes; envie os arquivos e pastas, não o ZIP inteiro).
4. Confirme em **Commit changes**.
5. Abra a aba **Actions**, selecione **Build GG Mouse Mobile APK** e toque em **Run workflow** se não iniciar automaticamente.
6. Quando terminar, abra a execução e baixe o artefato **GG-Mouse-Mobile-debug**. Ele é um ZIP com `app-debug.apk`.
7. Extraia o ZIP, toque no APK e autorize a instalação se o Android solicitar.

O GitHub pode pedir login e confirmação de segurança. Não compartilhe senha nem códigos de autenticação.
