# Ghibli Movies App

## 📌 Sobre o Projeto
O **Ghibli Movies App** é um aplicativo desenvolvido em **Jetpack Compose** para exibir informações sobre os filmes do **Studio Ghibli**. Ele consome uma API para listar os filmes, permitindo que os usuários visualizem detalhes como direção, ano de lançamento, descrição e pontuação.

## 🚀 Tecnologias Utilizadas
- **Kotlin**
- **Jetpack Compose** (UI declarativa)
- **MVVM (Model-View-ViewModel)**
- **Coroutines + Flow** (para manipulação assíncrona de dados)
- **Retrofit** (requisições HTTP)
- **Coil** (carregamento de imagens)
- **Material3** (design moderno)

## 📸 Screenshots
📌 ![image](https://github.com/user-attachments/assets/abc7f8a5-8125-4623-9c1b-4656062bc3c2) ![image](https://github.com/user-attachments/assets/2ed9f880-15aa-450d-bb40-181428f6f8e7)


## 🎥 Funcionalidades
✅ Listagem de filmes do Studio Ghibli
✅ Visualização detalhada de cada filme
✅ Atualização dinâmica dos dados
✅ Navegação entre telas
✅ Carregamento otimizado de imagens com Coil

## 🏗 Arquitetura
O aplicativo segue o padrão **MVVM (Model-View-ViewModel)**:
```
com.example.ghiblimoviesapp
│── data
│   ├── model (Modelos de dados)
│   ├── repository (Lógica de acesso a dados)
│── ui
│   ├── screens (Telas do app)
│   ├── viewmodel (ViewModels para cada tela)
```

## 🔧 Como Executar o Projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/KarolDiniz/ghibli-movies-app.git
   ```
2. Abra o projeto no **Android Studio**
3. Construa e execute o app em um emulador ou dispositivo físico
---
Feito com ❤️ por [Karol Diniz]!
