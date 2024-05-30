<template>
  <el-scrollbar height="100%" style="width: 100%;">
    <!-- 标题和搜索框 -->
    <div style="margin-top: 20px; margin-left: 40px; font-size: 2em; font-weight: bold; ">图书管理
      <el-input v-model="toSearch" :prefix-icon="Search"
                style=" width: 15vw;min-width: 150px; margin-left: 30px; margin-right: 30px; float: right;" clearable />
    </div>

    <div style="width:30%;margin:0 auto; padding-top:5vh;">
      根据你想要的条件查询
      <el-button style="margin-left: 10px;" type="primary" @click="newbookInfo.title = '', newbookInfo.maxPublishYear = '', newbookInfo.minPublishYear = '', newbookInfo.category = '', newbookInfo.minPrice='', newbookInfo.maxPrice='',newbookInfo.author='',newbookInfo.press='',this.query=true">图书查询</el-button>

    </div>
    <!-- 图书管理显示区 -->
    <div style="display: flex;flex-wrap: wrap; justify-content: start;">


      <!-- 书卡片 -->
      <div class="cardBox" v-for="book in books" v-show="book.title.includes(toSearch)" :key="book.bookId">
        <div>
          <!-- shuhao标题//书号, 类别, 书名, 出版社, 年份, 作者, 价格, 剩余库存 -->

          <div style="font-size: 25px; font-weight: bold;">No. {{ book.bookId }}</div>

          <el-divider />

          <!-- 卡片内容 -->
          <div style="margin-left: 10px; text-align: start; font-size: 16px;">
            <p style="padding: 2.5px;"><span style="font-weight: bold;">书号：</span>{{ book.bookId }}</p>
            <p style="padding: 2.5px;"><span style="font-weight: bold;">类别：</span>{{ book.category }}</p>
            <p style="padding: 2.5px;"><span style="font-weight: bold;">书名：</span>{{ book.title }}</p>
            <p style="padding: 2.5px;"><span style="font-weight: bold;">出版社：</span>{{ book.press }}</p>
            <p style="padding: 2.5px;"><span style="font-weight: bold;">作者：</span>{{ book.author }}</p>
            <p style="padding: 2.5px;"><span style="font-weight: bold;">价格：</span>{{ book.price }}</p>
            <p style="padding: 2.5px;"><span style="font-weight: bold;">剩余库存：</span>{{book.stock }}</p>
            <p style="padding: 2.5px;"><span style="font-weight: bold;">出版年份：</span>{{book.publishYear }}</p>
          </div>

          <el-divider />

          <div style="margin-top: 10px;">
            <el-button type="primary" :icon="Edit" @click="this.toModifyInfo.bookId = book.bookId, this.toModifyInfo.category = book.category,
                this.toModifyInfo.title = book.title, this.toModifyInfo.press = book.press,
                this.toModifyInfo.price = book.price, this.toModifyInfo.publishYear = book.publishYear,
                this.toModifyInfo.author = book.author,this.toModifyInfo.stock = book.stock,
                this.modifybookinfoVisible = true" circle  style="font-size: 20px; padding: 20px 20px;"/>

            <el-button type="danger" :icon="Delete" circle
                       @click="this.toRemove = book.bookId, this.removebookVisible = true"
                         style="font-size: 20px; padding: 20px 20px;margin-left: 10px;"/>

            <el-button  style="font-size: 11px; padding: 20px 30px;margin-left: 10px;" type="primary" :icon="Edit" @click="this.toModifyInfo.bookId = book.bookId, this.toModifyInfo.category = book.category,
                this.toModifyInfo.title = book.title, this.toModifyInfo.press = book.press,
                this.toModifyInfo.price = book.price, this.toModifyInfo.publishYear = book.publishYear,
                this.toModifyInfo.author = book.author,this.toModifyInfo.stock = book.stock,
                this.modifybookstockVisible = true" circle >
              修改存量
            </el-button>

          </div>
        </div>

      </div>
      <el-button class="newCardBox"
                 @click="newbookInfo.title = '', newbookInfo.publishYear = 0, newbookInfo.category = '',newbookInfo.stock=0, newbookInfo.price=0,newbookInfo.author='',newbookInfo.press='', newbookVisible = true">
        <el-icon style="height: 50px; width: 50px;">
          <Plus style="height: 100%; width: 100%;" />
        </el-icon>
      </el-button>
    </div>

    <el-dialog v-model="query" title="图书查询" width="30%" align-center>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        书名：
        <el-input v-model="newbookInfo.title" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        种类：
        <el-input v-model="newbookInfo.category" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        作者：
        <el-input v-model="newbookInfo.author" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        出版社：
        <el-input v-model="newbookInfo.press" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        最小价格：
        <el-input v-model="newbookInfo.minPrice" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        最大价格：
        <el-input v-model="newbookInfo.maxPrice" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        最小出版年份：
        <el-input v-model="newbookInfo.minPublishYear" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        最大出版年份：
        <el-input v-model="newbookInfo.maxPublishYear" style="width: 12.5vw;" clearable />
      </div>


      <template #footer>
                <span>
                    <el-button @click="query = false">取消</el-button>
                    <el-button type="primary" @click="mulQuery"
                               >确定</el-button>
                </span>
      </template>
    </el-dialog>

    <el-dialog v-model="newbookVisible" title="新建图书" width="30%" align-center>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        书名：
        <el-input v-model="newbookInfo.title" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        种类：
        <el-input v-model="newbookInfo.category" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        作者：
        <el-input v-model="newbookInfo.author" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        出版社：
        <el-input v-model="newbookInfo.press" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        价格：
        <el-input v-model="newbookInfo.price" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        出版年份：
        <el-input v-model="newbookInfo.publishYear" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        存量：
        <el-input v-model="newbookInfo.stock" style="width: 12.5vw;" clearable />
      </div>

      <template #footer>
                <span>
                    <el-button @click="newbookVisible = false">取消</el-button>
                    <el-button type="primary" @click="ConfirmNewbook"
                               :disabled="newbookInfo.category.length === 0 || newbookInfo.title.length === 0">确定</el-button>
                </span>
      </template>
    </el-dialog>

    <!-- 删除图书对话框 -->
    <el-dialog v-model="removebookVisible" title="删除图书" width="30%">
      <span>确定删除<span style="font-weight: bold;">{{ toRemove }}号图书</span>吗？</span>

      <template #footer>
                <span class="dialog-footer">
                    <el-button @click="removebookVisible = false">取消</el-button>
                    <el-button type="danger" @click="ConfirmRemovebook">
                        删除
                    </el-button>
                </span>
      </template>
    </el-dialog>

    <el-dialog v-model="modifybookinfoVisible" title="修改图书信息" width="30%" align-center>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        书名：
        <el-input v-model="toModifyInfo.title" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        种类：
        <el-input v-model="toModifyInfo.category" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        作者：
        <el-input v-model="toModifyInfo.author" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        出版社：
        <el-input v-model="toModifyInfo.press" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        价格：
        <el-input v-model="toModifyInfo.price" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        出版年份：
        <el-input v-model="toModifyInfo.publishYear" style="width: 12.5vw;" clearable />
      </div>

      <template #footer>
                <span>
                    <el-button @click="modifybookinfoVisible = false">取消</el-button>
                    <el-button type="primary" @click="modifybook"
                               :disabled="toModifyInfo.category.length === 0 || toModifyInfo.title.length === 0">确定</el-button>
                </span>
      </template>
    </el-dialog>

    <el-dialog v-model="modifybookstockVisible" title="修改图书信息" width="30%" align-center>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        存量：
        <el-input v-model="toModifyInfo.stock" style="width: 12.5vw;" clearable />
      </div>

      <template #footer>
                <span>
                    <el-button @click="modifybookstockVisible = false">取消</el-button>
                    <el-button type="primary" @click="modifybookstock"
                               :disabled="toModifyInfo.stock.length === 0 ">确定</el-button>
                </span>
      </template>
    </el-dialog>

  </el-scrollbar>
</template>

<script>
import { Delete, Edit, Search } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'
/*
private int bookId;
    private String category;
    private String title;
    private String press;
    private int publishYear;
    private String author;
    private double price;
    private int stock;
 */
export default {
  data() {
    return {
      books: [],
      Delete,
      query:false,
      Edit,
      Search,
      toSearch: '', // 搜索内容
      newbookVisible: false, // 新建借书证对话框可见性
      removebookVisible: false, // 删除借书证对话框可见性
      modifybookinfoVisible: false,
      modifybookstockVisible: false,

      toRemove: 0, // 待删除借书证号
      newbookInfo: { // 待新建借书证信息
        category: '',
        title: '',
        press:'',
        publishYear: 0,
        author:'',
        price:0,
        stock: 0,
        minPublishYear:0,
        maxPublishYear:0,
        minPrice:0,
        maxPrice:0,

      },
      toModifyInfo: { // 待修改借书证信息
        category: 'computer',
        title: '小明',
        press:'zju',
        publishYear: 1979,
        author:'ccyk',
        price:90,
        stock: 6
      },
    }
  },
  methods: {
    ConfirmNewbook() {
     // ElMessage.success("图书新建成功")
      let response =axios.post("/insertbook",
          { // 请求体
            category: this.newbookInfo.category,
            title: this.newbookInfo.title,
            press: this.newbookInfo.press,
            publishYear: this.newbookInfo.publishYear,
            author: this.newbookInfo.author,
            price: this.newbookInfo.price,
            stock: this.newbookInfo.stock,
          })
          .then(response => {
            let back=response.data;
            console.log(back);
            if(back=="1")
            {
              ElMessage.success("图书新建成功") // 显示消息提醒
              this.newbookVisible = false // 将对话框设置为不可见
              this.Querybooks() // 重新查询借书证以刷新页面
            }
            else
            {
              alert("You have registered the same book before");
            }
          }).catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            alert("An error occurred");
          });
    },

    modifybook() {
      let response =axios.post("/modifybook",
          { // 请求体
            bookId:this.toModifyInfo.bookId,
            category: this.toModifyInfo.category,
            title: this.toModifyInfo.title,
            press: this.toModifyInfo.press,
            publishYear: this.toModifyInfo.publishYear,
            author: this.toModifyInfo.author,
            price: this.toModifyInfo.price,
            stock: this.toModifyInfo.stock,
          })
          .then(response => {
            let back=response.data;
            console.log(back);
            if(back=="1")
            {
              ElMessage.success("图书修改成功") // 显示消息提醒
              this.modifybookinfoVisible = false // 将对话框设置为不可见
              this.Querybooks() // 重新查询借书证以刷新页面
            }
            else
            {
              alert("修改失败");
            }
          }).catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            alert("An error occurred");
          });
    },
    modifybookstock() {
      let response =axios.post("/modifybookstock",
          { // 请求体
            bookId:this.toModifyInfo.bookId,
            category: this.toModifyInfo.category,
            title: this.toModifyInfo.title,
            press: this.toModifyInfo.press,
            publishYear: this.toModifyInfo.publishYear,
            author: this.toModifyInfo.author,
            price: this.toModifyInfo.price,
            stock: this.toModifyInfo.stock,
          })
          .then(response => {
            let back=response.data;
            console.log(back);
            if(back=="1")
            {
              ElMessage.success("图书存量修改成功") // 显示消息提醒
              this.modifybookstockVisible = false // 将对话框设置为不可见
              this.Querybooks() // 重新查询借书证以刷新页面
            }
            else
            {
              alert("修改失败");
            }
          }).catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            alert("An error occurred");
          });
    },
    ConfirmRemovebook() {
      // 发出POST请求删除借书证
      axios.post("/removebook", {
        bookId: this.toRemove
      })
          .then(response => {
            let result = response.data;
            console.log(result);
            if (result == "1") {
              ElMessage.success("图书删除成功");
              this.removebookVisible = false;
              this.Querybooks();
            } else {
              ElMessage.error("删除失败");
            }
          })
          .catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            ElMessage.error("操作时发生错误");
          });
    },
    Querybooks() {
      this.books = [] // 清空列表
      let response = axios.get('/book1') // 向/card发出GET请求
          .then(response => {
            let books = response.data // 接收响应负载
            books.forEach(book => { // 对于每个借书证
              this.books.push(book) // 将其加入到列表中


            })
          })
          .catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            alert("An error occurred");
          });
    },
    mulQuery() {
      this.books = [] // 清空列表
      let response = axios.post('/bq',{
        category: this.newbookInfo.category,
        title: this.newbookInfo.title,
        press: this.newbookInfo.press,
        author: this.newbookInfo.author,

        minPrice: this.newbookInfo.minPrice,
        maxPrice: this.newbookInfo.maxPrice,
        minPublishYear: this.newbookInfo.minPublishYear,
        maxPublishYear:this.newbookInfo.maxPublishYear
      }) // 向/card发出GET请求
          .then(response => {
            let books = response.data // 接收响应负载
            books.forEach(book => { // 对于每个借书证
              this.books.push(book) // 将其加入到列表中
            })
            ElMessage.success("图书查询成功");
            this.query = false;
          })
          .catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            alert("An error occurred");
          });
    },

  },

  mounted() { // 当页面被渲染时
    this.Querybooks() // 查询借书
  }
}

</script>


<style scoped>
.cardBox {
  height: 450px;
  width: 200px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  text-align: center;
  margin-top: 40px;
  margin-left: 27.5px;
  margin-right: 10px;
  padding: 7.5px;
  padding-right: 10px;
  padding-top: 15px;
}

.newCardBox {
  height: 450px;
  width: 200px;
  margin-top: 40px;
  margin-left: 27.5px;
  margin-right: 10px;
  padding: 7.5px;
  padding-right: 10px;
  padding-top: 15px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  text-align: center;
}
</style>