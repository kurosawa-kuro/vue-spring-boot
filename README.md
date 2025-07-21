# Vue + Spring Boot フルスタックアプリケーション

Vue 3フロントエンドとSpring Bootバックエンドを連携させたサンプルアプリケーションです。

## 技術スタック

### フロントエンド
- Vue 3 (Composition API)
- Pinia (状態管理)
- Tailwind CSS (スタイリング)

### バックエンド
- Java 17+
- Spring Boot (Web, Data JPA)
- Maven (ビルドツール)
- SQLite (データベース)
- Springdoc OpenAPI (API仕様書)

### データモデル
- **User**: id, name
- **Micropost**: id, title, userId
- **リレーション**: User 1 : * Micropost

## 前提条件

以下のソフトウェアがインストールされている必要があります：

- Node.js (v16以上)
- npm または yarn
- Java 17以上
- Maven 3.6以上

## セットアップ手順

### 1. リポジトリのクローン
```bash
git clone https://github.com/kurosawa-kuro/vue-spring-boot.git
cd vue-spring-boot
```

### 2. 依存関係のインストール

#### フロントエンド
```bash
cd src/frontend
npm install
cd ../..
```

#### バックエンド
```bash
cd src/backend/vue-spring-boot-backend
mvn clean install
cd ../../..
```

#### ルートディレクトリ
```bash
npm install
```

## 起動方法

### 同時起動（推奨）
```bash
npm run dev
```

このコマンドで以下が同時に起動されます：
- フロントエンド: http://localhost:5173
- バックエンド: http://localhost:3001

### 個別起動

#### フロントエンドのみ
```bash
npm run frontend
```

#### バックエンドのみ
```bash
npm run backend
```

## 動作確認

### 1. フロントエンドUI
- ブラウザで http://localhost:5173 にアクセス
- 投稿一覧の表示確認
- 新規投稿の作成確認

### 2. API エンドポイント
```bash
# ユーザー一覧取得
curl http://localhost:3001/users

# 投稿一覧取得
curl http://localhost:3001/microposts

# 新規投稿作成
curl -X POST http://localhost:3001/microposts \
  -H "Content-Type: application/json" \
  -d '{"title": "テスト投稿", "userId": 1}'
```

### 3. API仕様書
- Swagger UI: http://localhost:3001/swagger-ui/index.html

## データベース

- SQLiteファイル: `src/backend/vue-spring-boot-backend/data/app.db`
- 初期データは起動時に自動作成されます
- テーブル構造はJPAエンティティから自動生成されます

## API エンドポイント

| メソッド | エンドポイント | 説明 |
|---------|---------------|------|
| GET | `/users` | ユーザー一覧取得 |
| GET | `/microposts` | 投稿一覧取得 |
| GET | `/microposts/{id}` | 特定投稿取得 |
| POST | `/microposts` | 新規投稿作成 |

## プロジェクト構成

```
vue-spring-boot/
├── src/
│   ├── frontend/          # Vue 3 アプリケーション
│   │   ├── src/
│   │   │   ├── views/     # ページコンポーネント
│   │   │   ├── stores/    # Pinia ストア
│   │   │   └── schemas/   # Zod バリデーション
│   │   └── package.json
│   └── backend/           # Spring Boot アプリケーション
│       └── vue-spring-boot-backend/
│           ├── src/main/java/com/example/
│           │   ├── controller/    # REST コントローラー
│           │   ├── entity/        # JPA エンティティ
│           │   ├── repository/    # データリポジトリ
│           │   ├── dto/           # データ転送オブジェクト
│           │   └── config/        # 設定クラス
│           └── pom.xml
├── package.json           # 同時起動スクリプト
└── README.md
```

## 開発メモ

- フロントエンドは既存コードを流用
- 認証機能は実装していません
- 更新・削除機能は実装していません
- CORS設定により localhost:5173 からのアクセスを許可
- Jackson設定でLocalDateTimeをISO 8601形式でシリアライズ
