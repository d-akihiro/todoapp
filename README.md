# このプロジェクトについて

単純なTODOアプリです。

# 開発環境
- Java8
- postgresql

# postgresqlインストール
- プロジェクトのビルド実行時にDBが動作している必要がある。
(flywayでdb定義変更を管理するため)
- `todolist-db`という名前でDBを作成
- DBの接続ポートは5432(デフォルト)
- 作成したDBを使用するユーザを作成(ユーザ名:postgres, パスワード:postgres)
- postgresqlのスーパーユーザで`create extension crypt`を実行
(SQLでパスワードのハッシュ化するために必要)

# ビルド方法

## windows
1. build.gradleのあるディレクトリへ移動
2. `gradlew build`

## mac, linux
1. build.gradleのあるディレクトリへ移動
2. `gradle build`

# 実行
## 単独実行
ビルド時に組み込まれるtomcatで単独動作が可能です。

### windows
1. build.gradleのあるディレクトリへ移動
2. `gradlew bootRun`

### mac, linux
1. build.gradleのあるディレクトリへ移動
2. `gradle bootRun`

## 別のtomcatで実行
ビルドで出力される`build/libs/todolist.war`をtomcatへデプロイすることで動作する。
(…はず。未確認)

## 画面表示
- `localhost:8080`で画面表示
- 初期ユーザはユーザ名admin, パスワードadmin
