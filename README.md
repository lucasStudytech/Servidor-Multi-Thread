# Projeto de Chat

Este projeto é um sistema de chat simples que permite a comunicação entre múltiplos clientes conectados a um servidor. O projeto é dividido em várias classes que trabalham juntas para fornecer a funcionalidade de chat.

## Estrutura do Projeto

O projeto é composto pelos seguintes arquivos e suas respectivas responsabilidades:

### 1. `EstruturaClientes.java`

**Responsabilidade:** Gerencia as informações e a comunicação dos clientes conectados.

**Descrição:**

- Armazena detalhes sobre o cliente, como ID, nome e conexões de entrada/saída.
- Possui métodos para adicionar e remover clientes da lista de clientes conectados.
- Implementa a interface `Runnable` para permitir a execução em uma thread separada.
- Gerencia a transmissão de mensagens para todos os clientes conectados.

**Métodos principais:**

- `addClient()`: Adiciona o cliente à lista de clientes conectados.
- `removeClient()`: Remove o cliente da lista.
- `broadcastMessage(String message)`: Envia uma mensagem para todos os clientes, exceto o remetente.

### 2. `Servidor.java`

**Responsabilidade:** Executa o servidor de chat, aceita conexões de clientes e gerencia a comunicação.

**Descrição:**

- Cria um `ServerSocket` que escuta por conexões na porta 3030.
- Quando um cliente se conecta, cria uma instância de `EstruturaClientes` para gerenciar o cliente.
- Inicia uma nova thread para cada cliente usando a classe `EstruturaClientes`.

**Fluxo principal:**

- Aceita conexões de clientes e cria objetos `EstruturaClientes`.
- Adiciona o cliente à lista de clientes conectados.
- Inicia uma thread para gerenciar a comunicação com o cliente.

### 3. `ClienteUm.java` e `ClienteDois.java`

**Responsabilidade:** Representam clientes que se conectam ao servidor para enviar e receber mensagens.

**Descrição:**

- Estabelecem uma conexão com o servidor na porta 3030.
- Criam uma thread para ler mensagens do servidor e exibi-las.
- Permitem ao usuário digitar mensagens para enviar ao servidor.

**Fluxo principal:**

- Conectam-se ao servidor.
- Iniciam uma thread para receber mensagens do servidor.
- Leem entradas do usuário e enviam para o servidor.

### 4. `Mensagem.java`

**Responsabilidade:** Gerencia a leitura de mensagens recebidas do servidor.

**Descrição:**

- Implementa a interface `Runnable` para permitir a execução em uma thread separada.
- Lê mensagens do `BufferedReader` associado ao servidor e as exibe.

**Métodos principais:**

- `run()`: Loop contínuo para ler e exibir mensagens recebidas.

## Relação Entre Arquivos

- **Servidor.java**: É o ponto de entrada do sistema. Ele aceita conexões de clientes e cria instâncias de `EstruturaClientes` para gerenciar a comunicação.
  
- **EstruturaClientes.java**: Gerencia cada cliente individualmente. Adiciona e remove clientes da lista de clientes conectados e lida com a transmissão de mensagens.

- **ClienteUm.java** e **ClienteDois.java**: Representam os clientes que se conectam ao servidor, enviam mensagens e recebem atualizações.

- **Mensagem.java**: Utilizado pelos clientes para ler mensagens recebidas do servidor.

## Configuração do Maven

O projeto utiliza o Maven para gerenciamento de dependências e construção do projeto. A configuração do Maven está descrita no arquivo `pom.xml`, que inclui dependências para testes e o plugin para execução e empacotamento.

---

Este projeto demonstra como construir um sistema de chat simples usando sockets em Java. Cada classe tem uma função específica e trabalha em conjunto para fornecer a funcionalidade completa do chat.
