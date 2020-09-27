<template>
  <div class="hello">
    <b-card header="오늘 해야 할 일" style="max-width: 40rem; margin: auto; margin-top: 10vh;"
            class="mb-2"
            border-variant="info"
            align="left"
      >
      <b-form-group id="to-do-input">
        <b-container fluid>
          <b-row class="my-1">
            <b-col sm="10">
              <b-form-input v-model="newToDoItemRequest.title" type="text" placeholder="새 할일을 적으세요."
              v-on:keyup.enter="createTodo"/>
            </b-col>
            <b-col sm="2">
              <b-button variant="outline-primary" v-on:click="createTodo">추가</b-button>
            </b-col>
          </b-row>
        </b-container>
      </b-form-group>

      <b-list-group v-if="todoItems && todoItems.length">
        <b-list-group-item
          v-for="todoItem of todoItems"
          v-bind:data="todoItem.taskNo"
          v-bind:key="todoItem.taskNo" style="display: flex;">
            <b-form-checkbox
              v-model="todoItem.done"
            v-on:change="markDone(todoItem)">
              <span v-if="todoItem.done" style="text-decoration: line-through; color: #D3D3D3;">
              {{todoItem.title}}
              </span>
              <span v-else>
                {{todoItem.title}}
              </span>
            </b-form-checkbox>
        </b-list-group-item>
      </b-list-group>
    </b-card>
  </div>
</template>

<script>
  import axios from 'axios'

  // eslint-disable-next-line no-unused-vars
  let baseUrl = 'http://127.0.0.1:8090/todo'
export default {
    name: 'hello',
    data: () => {
      return {
        todoItems: [],
        // eslint-disable-next-line standard/object-curly-even-spacing
        newToDoItemRequest: { title: ''}
      }
    },
    methods: {
      initToDoList: function () {
        // eslint-disable-next-line no-unused-vars
        let vm = this
        axios.get(baseUrl)
          .then(response => {
            // eslint-disable-next-line no-unused-expressions
            vm.todoItems = response.data.data
          })
          .catch(e => {
            console.log('error : ', e)
          })
      },
      createTodo: function (event) {
        event.preventDefault()
        // eslint-disable-next-line no-unused-vars
        let vm = this

        if (!vm.newToDoItemRequest.title) return
        console.log(vm.newToDoItemRequest.title)
        axios.post(baseUrl, vm.newToDoItemRequest)
          .then(response => {
            console.log(response)
            vm.initToDoList()
            vm.newToDoItemRequest = {}
          })
          .catch(error => {
            console.log(error)
          })
      },
      markDone: function (toDoItem) {
        if (!toDoItem) return
        // eslint-disable-next-line no-unused-vars
        let vm = this
        toDoItem.done = !toDoItem.done

        axios.put(baseUrl, toDoItem)
          .then(response => {
            vm.initToDoList()
          })
          .catch(error => {
            console.log(error)
          })
      }
    },
    created () {
      this.initToDoList()
    }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #35495E;
}
</style>
