# Padrões de Projeto em Java: mini RPG por turnos

Trabalho de Aplicações para Internet. Implementa 7 padrões de projeto do catálogo GoF
dentro de um mini RPG de console: a heroína Lyra começa uma partida, enfrenta lobos na
Floresta Sombria, encanta uma arma nova e derrota um golem na Montanha Gelada. Cada
mecânica do jogo é resolvida por um padrão.

## Como rodar

Precisa do JDK 17 ou mais novo.

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -Dstdout.encoding=UTF-8 -cp out br.com.rpg.Main
```

No Windows, o jeito mais fácil é rodar o script, que acha o JDK sozinho:

```powershell
powershell -ExecutionPolicy Bypass -File .\rodar.ps1
```

Ou, com o JDK já no PATH:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -Dstdout.encoding=UTF-8 -cp out br.com.rpg.Main
```

Também abre no IntelliJ, NetBeans ou VS Code: marque `src` como pasta de código e rode
a classe `br.com.rpg.Main`.

## Padrões implementados

| Padrão | Tipo | Onde | Mecânica do jogo |
|---|---|---|---|
| Singleton | Criacional | `singleton/DiarioDeBatalha` | Um único diário registra todos os eventos da partida |
| Factory Method | Criacional | `factorymethod/Regiao` | Cada região do mapa decide qual inimigo nasce nela |
| Adapter | Estrutural | `adapter/JoystickAdapter` | Um joystick antigo, que só manda códigos de botão, controla o jogo |
| Decorator | Estrutural | `decorator/Encantamento` | Encantamentos empilháveis que mudam o dano e o nome da arma |
| Facade | Estrutural | `facade/NovaPartida` | Uma chamada prepara áudio, mapa, save, arma inicial e HUD |
| Strategy | Comportamental | `strategy/EstiloDeCombate` | Estilos agressivo, defensivo e equilibrado, trocados no meio da luta |
| Observer | Comportamental | `observer/OuvinteDoHeroi` | Barra de vida, música e conquistas reagem quando a vida muda |

### Singleton
`DiarioDeBatalha` tem construtor privado e é obtido por `getInstancia()`. Usa o idioma
*holder* (classe interna estática): a instância só é criada no primeiro uso e a JVM
garante que isso é seguro entre threads, sem `synchronized` no `getInstancia()`.
O herói, as regiões e a batalha escrevem no mesmo diário sem precisar recebê-lo.

### Factory Method
`Regiao` é abstrata. O método `gerarOnda()` é igual para todas, mas o inimigo vem do
método abstrato `criarInimigo()`, que cada subclasse implementa: `FlorestaSombria` cria
`Lobo` e `MontanhaGelada` cria `GolemDeGelo`. Para uma região nova, basta uma subclasse
nova, sem alterar código existente (princípio aberto/fechado). Isso é diferente de uma
*Simple Factory*, que seria um método estático com `switch`.

### Adapter
`JoystickRetro` simula o driver de um controle antigo que não podemos mudar: ele só
devolve códigos como `0x01`. O jogo espera a interface `Controle`, que devolve uma
`Acao`. `JoystickAdapter` implementa `Controle` e traduz cada código. A `Batalha`
nunca fica sabendo que existe um joystick.

### Decorator
`Arma` é a interface, `Espada` e `Lanca` são as armas básicas e cada `Encantamento`
embrulha outra arma. Como o encantamento também é uma `Arma`, eles se empilham:
`new BencaoLendaria(new RunaDeFogo(new Afiada(new Lanca())))`. A `BencaoLendaria`
multiplica o dano em vez de somar, então a ordem dos encantamentos muda o resultado
(o `Main` mostra 32 contra 23).

### Facade
`NovaPartida.iniciar("Lyra")` toca o áudio, carrega o mapa, cria o save, pede a arma ao
ferreiro, cria a heroína e inscreve a HUD nela. Os subsistemas são *package-private*,
então de fora do pacote o único caminho é a fachada.

### Strategy
O `Heroi` delega para o `EstiloDeCombate` atual o cálculo do dano causado e do dano
recebido. `Agressivo` bate e apanha 50% mais, `Defensivo` bate e apanha metade. Na
batalha, o botão B troca para defensivo e o Y para agressivo, sem nenhum `if` no herói.

### Observer
O `Heroi` é o sujeito: guarda uma lista de `OuvinteDoHeroi` e avisa todos quando a vida
muda. `BarraDeVida` desenha a barra, `TrilhaSonora` troca a música quando a vida cruza
30% e `Conquistas` desbloqueia conquistas. O herói não conhece nenhum deles.

## Estrutura

```
src/br/com/rpg/
├── Main.java            a aventura que demonstra os 7 padrões
├── Batalha.java         loop de batalha por turnos
├── personagem/          Heroi e Inimigo
├── singleton/
├── factorymethod/
├── adapter/
├── decorator/
├── facade/
├── strategy/
└── observer/
```
