# Android RPG

## 概要
BluetoothSocket通信を使ったカードゲーム

## プレイ画面について
画面上部のモンスターが敵
画面下部の6体が自陣のモンスターで、上の4体は場に出ている状態。左下の2体は控え。

## 遊び方
### モード
- 1人で対戦(未実装)
- 2人で対戦(ローカル)
- 2人で対戦(Bluetooth)

#### 2人で対戦(ローカル)
1台のスマートフォンを渡し合いながら遊べるモード

#### 2人で対戦(Bluetooth)
2台のスマートフォンで通信対戦ができるモード

### 基本操作
- モンスターをタップして、敵を選択すると攻撃できる
- 控えのモンスターをタップして、どのモンスターと交代するか決定する。
- モンスターの画像の左下の数字が攻撃力、右下の数字がモンスターのHP。モンスターのHPが0になると、プレイヤーにダイレクトアタックができる。
- 行動が終了すると、タップできなくなる。(交代は可能。)4ターン経過すると復活する。

### Bluetooth通信対戦について
- まず、設定からスマートフォンどうしをペアリングする
- 片方の端末で、一番上の「接続できる端末を検索(SECURE)」をタップし、ペアリングする端末を選択する。
- しばらくするとConnectedするので、ゲーム開始ボタンをタップしゲーム開始する
- (現在はランダムにプレイヤー1と2を割り振る仕様になっている)

## 注意点
Bluetooth通信でたまに落ちることがあるので、その場合はアプリを終了し、再度接続します。